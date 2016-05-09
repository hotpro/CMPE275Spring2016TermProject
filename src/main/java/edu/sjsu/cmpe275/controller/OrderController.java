package edu.sjsu.cmpe275.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import edu.sjsu.cmpe275.dao.MenuItemDao;
import edu.sjsu.cmpe275.dao.OrderDao;
import edu.sjsu.cmpe275.dao.OrderItemDao;
import edu.sjsu.cmpe275.domain.MenuItem;
import edu.sjsu.cmpe275.domain.Order;
import edu.sjsu.cmpe275.domain.OrderItem;
import edu.sjsu.cmpe275.domain.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

import static javax.swing.text.html.CSS.getAttribute;


/**
 * Created by yutao on 5/5/16.
 */
@Controller
@RequestMapping("/order")
public class OrderController {

    private static final Logger logger = LoggerFactory.getLogger(OrderController.class);

    /** ONE_DAY_WORK is 15 hours long. unit is millisecond*/
    private static final long ONE_DAY_WORK = (21 - 6) * 60 * 60 * 1000;

    @Autowired
    private OrderDao orderDao;

    @Autowired
    private MenuItemDao menuItemDao;

    @Autowired
    private OrderItemDao orderItemDao;

    @RequestMapping(value = "/getEarliestPickupTime", method = RequestMethod.POST)
    public @ResponseBody
    PickupTimeTO getEarliestPickupTime(@RequestBody String body) throws IOException {
        logger.debug("body: {}", body);
        logger.debug("type:{}",body.getClass().getName());


        List<OrderTO> orderTOList = new ObjectMapper().readValue(body, new TypeReference<List<OrderTO>>(){});

        /*
        test case:
        1. empty order. 1. order before 6am. 2. order after 6am before 9pm. 3. order after 9pm
         */

        int totalTime = calculateTotalTime(orderTOList);

        // order take too long time
        if (totalTime > ONE_DAY_WORK) {
            // TODO:
        }

        List<List<Order>> orderListOfAllChef = getOrderListOfAllChef(orderDao, Calendar.getInstance().getTime().getTime());

        // TODO: need to consider the case that order start after 30 days
        long earliestStartTime = Long.MAX_VALUE;
        long now = LocalDateTime.now().atZone(TimeZone.getDefault().toZoneId()).toInstant().toEpochMilli();
        for (List<Order> list : orderListOfAllChef) {
            long startTime = findEarliestStartTime(now, totalTime, list);
            earliestStartTime = Math.min(earliestStartTime, startTime);
        }
        long earliestPickupTime = earliestStartTime + totalTime;
        logger.debug("getEarliestPickupTime earliestStartTime: {}, totalTime: {}, earliestPickupTime: {}", earliestStartTime,
                totalTime, earliestPickupTime);
        logger.debug("getEarliestPickupTime earliestStartTime: {}, totalTime: {}, earliestPickupTime: {}", timestampToString(earliestStartTime),
                totalTime, timestampToString(earliestPickupTime));

        return new PickupTimeTO(earliestPickupTime, "");
    }

    private List<List<Order>> getOrderListOfAllChef(OrderDao orderDao, long startTime) {
        List<Order> order0 = new ArrayList<>();
        List<Order> order1 = new ArrayList<>();
        List<Order> order2 = new ArrayList<>();
        List<Order> allOrderList = orderDao.findByFinishTimeGreaterThanEqual(new Date(startTime));
        for (Order order : allOrderList) {
            switch (order.getChiefId()) {
                case 0:
                    order0.add(order);
                    break;
                case 1:
                    order1.add(order);
                    break;
                case 2:
                    order2.add(order);
                    break;
                default:
                    order0.add(order);
                    break;
            }
        }

        List<List<Order>> orderListOfAllChef = new ArrayList<>();
        orderListOfAllChef.add(order0);
        orderListOfAllChef.add(order1);
        orderListOfAllChef.add(order2);
        return orderListOfAllChef;
    }
    private static long localDateTimeToTimeStamp(LocalDateTime localDateTime) {
        return LocalDateTime.now().atZone(TimeZone.getDefault().toZoneId()).toInstant().toEpochMilli();
    }

    private LocalDateTime timestampToLocalDateTime(long timestamp) {
        return LocalDateTime.ofInstant(Instant.ofEpochMilli(timestamp), TimeZone.getDefault().toZoneId());
    }

    private String timestampToString(long timestamp) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        return LocalDateTime.ofInstant(Instant.ofEpochMilli(timestamp), TimeZone.getDefault().toZoneId()).format(formatter);
    }

    private int calculateTotalTime(List<OrderTO> orderTOList) {
        int totalTime = 0;
        for (OrderTO orderTO : orderTOList) {
            totalTime += menuItemDao.findOne(orderTO.getMenuId()).getPreparationTime();
            logger.debug("prepareTime:{}",menuItemDao.findOne(orderTO.getMenuId()).getPreparationTime());
        }
        return totalTime * 60 * 1000;
    }

    /**
     *
     * @param startTime milliseconds
     * @param totalTime milliseconds
     * @param orderList
     * @return
     */
    private long findEarliestStartTime(long startTime, long totalTime, List<Order> orderList) {

        for (Order o : orderList) {
            if (startTime + totalTime < o.getStartPrepareTime().getTime()
                    && isInOpenHours(startTime, startTime + totalTime)) {
                return startTime;
            } else {
                startTime = o.getStartPrepareTime().getTime() + o.getTotalTime();
            }
        }

        // if orderList is empty, or reach to the last one
        if (isInOpenHours(startTime, startTime + totalTime)) {
            return startTime;
        }

        // delay it to open hours
        LocalDateTime start = LocalDateTime.ofInstant(Instant.ofEpochMilli(startTime), TimeZone.getDefault().toZoneId());
        LocalDateTime open = start.withHour(6).withMinute(0).withSecond(0).withNano(0);

        // if too early, delay it to 6am
        if (start.compareTo(open) < 0) {
            ZonedDateTime zonedDateTime = open.atZone(TimeZone.getDefault().toZoneId());
            return zonedDateTime.toInstant().toEpochMilli();
        }

        //if too late, delay it to 6am in next day
        LocalDateTime nextDayOpen = open.plusDays(1);
        return nextDayOpen.atZone(TimeZone.getDefault().toZoneId()).toInstant().toEpochMilli();
    }

    private boolean isInOpenHours(long startTime, long endTime) {
        LocalDateTime start = LocalDateTime.ofInstant(Instant.ofEpochMilli(startTime), TimeZone.getDefault().toZoneId());
        LocalDateTime end = LocalDateTime.ofInstant(Instant.ofEpochMilli(endTime), TimeZone.getDefault().toZoneId());

        LocalDateTime open = start.withHour(6).withMinute(0).withSecond(0).withNano(0);
        LocalDateTime close = start.withHour(21).withMinute(0).withSecond(0).withNano(0);
        if (start.compareTo(open) >= 0 && end.compareTo(close) <= 0) {
            return true;
        }
        return false;
    }

    @RequestMapping(value = "/submit", method = RequestMethod.POST)
    public @ResponseBody SubmitOrderResult submit(@RequestBody SubmitOrderTO submitOrderTO,
                                                  HttpSession httpSession) {
//        User user = (User)httpSession.getAttribute("USER");
        Date orderTime = Calendar.getInstance().getTime();
        List<OrderTO> orderTOList = submitOrderTO.getOrderTOList();
        int totalTime = calculateTotalTime(orderTOList);
        LocalDateTime idealEarliestStartTime = timestampToLocalDateTime(submitOrderTO.getPickupTime())
                .minusHours(1).minusSeconds(totalTime / 1000);

        List<List<Order>> orderListOfAllChef = getOrderListOfAllChef(orderDao, localDateTimeToTimeStamp(idealEarliestStartTime));

        // TODO: need to consider the case that order start after 30 days
        long earliestStartTime = Long.MAX_VALUE;
        int chefId = 0;
        for (int i = 0; i < 3; i++) {
            List<Order> list = orderListOfAllChef.get(i);
            long startTimeForThisChef = findEarliestStartTime(localDateTimeToTimeStamp(idealEarliestStartTime), totalTime, list);
            if (startTimeForThisChef < earliestStartTime) {
                chefId = i;
                earliestStartTime = startTimeForThisChef;
            }
        }
        long earliestPickupTime = earliestStartTime + totalTime;
        logger.debug("submit chefId: {}, earliestStartTime: {}, totalTime: {}, earliestPickupTime: {}", chefId, earliestStartTime,
                totalTime, earliestPickupTime);
        logger.debug("submit echefId: {}, arliestStartTime: {}, totalTime: {}, earliestPickupTime: {}", chefId,
                timestampToString(earliestStartTime),
                totalTime, timestampToString(earliestPickupTime));

        double totalPrice = 0.0;
        for (OrderTO orderTO : orderTOList) {
            totalPrice += menuItemDao.findOne(orderTO.getMenuId()).getUnitPrice() * orderTO.getCount();
        }
        List<OrderItem> orderItemList = new ArrayList<>();
        Order order = new Order(new Date(earliestPickupTime), orderTime, totalPrice, totalTime, orderItemList, null, chefId,
                new Date(earliestStartTime), new Date(earliestStartTime + totalTime));
        orderDao.save(order);

        for (OrderTO orderTO : orderTOList) {
            MenuItem menuItem = menuItemDao.findOne(orderTO.getMenuId());
            OrderItem orderItem = new OrderItem(new Date(earliestPickupTime), orderTime, null, order, menuItem,
                    new BigDecimal(menuItem.getUnitPrice()), menuItem.getPreparationTime() * orderTO.getCount(),
                    orderTO.getCount());
            orderItemDao.save(orderItem);
        }

        return new SubmitOrderResult(0, "We've received your order. Have a nice day :)");
    }

    static class SubmitOrderTO {
        private List<OrderTO> orderTOList;
        private long pickupTime;

        public SubmitOrderTO() {
        }

        public SubmitOrderTO(List<OrderTO> orderTOList, long pickupTime) {
            this.orderTOList = orderTOList;
            this.pickupTime = pickupTime;
        }

        public List<OrderTO> getOrderTOList() {
            return orderTOList;
        }

        public void setOrderTOList(List<OrderTO> orderTOList) {
            this.orderTOList = orderTOList;
        }

        public long getPickupTime() {
            return pickupTime;
        }

        public void setPickupTime(long pickupTime) {
            this.pickupTime = pickupTime;
        }
    }

    static class OrderTO {
        long menuId;
        int count;

        public OrderTO() {
        }

        public OrderTO(int menuId, int count) {
            this.menuId = menuId;
            this.count = count;
        }

        public long getMenuId() {
            return menuId;
        }

        public void setMenuId(long menuId) {
            this.menuId = menuId;
        }

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }
    }

    static class SubmitOrderResult {
        int code;
        String message;

        public SubmitOrderResult() {
        }

        public SubmitOrderResult(int code, String message) {
            this.code = code;
            this.message = message;
        }

        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }
    }

    static class PickupTimeTO {
        public PickupTimeTO() {
        }

        public PickupTimeTO(long earliestPickupTime, String errorMsg) {
            this.earliestPickupTime = earliestPickupTime;
            this.errorMsg = errorMsg;
        }

        private long earliestPickupTime;
        private String errorMsg;

        public long getEarliestPickupTime() {
            return earliestPickupTime;
        }

        public void setEarliestPickupTime(long earliestPickupTime) {
            this.earliestPickupTime = earliestPickupTime;
        }

        public String getErrorMsg() {
            return errorMsg;
        }

        public void setErrorMsg(String errorMsg) {
            this.errorMsg = errorMsg;
        }
    }
}
