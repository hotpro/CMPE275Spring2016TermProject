package edu.sjsu.cmpe275.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import edu.sjsu.cmpe275.dao.MenuItemDao;
import edu.sjsu.cmpe275.dao.OrderDao;
import edu.sjsu.cmpe275.dao.OrderItemDao;
import edu.sjsu.cmpe275.dao.UserDao;
import edu.sjsu.cmpe275.domain.MenuItem;
import edu.sjsu.cmpe275.domain.Order;
import edu.sjsu.cmpe275.domain.OrderItem;
import edu.sjsu.cmpe275.domain.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

//import static com.sun.tools.javac.jvm.ByteCodes.ret;

/**
 * Created by yutao on 5/5/16.
 */
@Controller
@RequestMapping("/order")
public class OrderController {

    private static final Logger logger = LoggerFactory.getLogger(OrderController.class);

    /** ONE_DAY_WORK is 15 hours long. unit is millisecond*/
    private static final long ONE_DAY_WORK = (21 - 6) * 60 * 60 * 1000;

    private static final long MAX_TIME = 30L * 24 * 60 * 60 * 1000;

    @Autowired
    private OrderDao orderDao;

    @Autowired
    private MenuItemDao menuItemDao;

    @Autowired
    private OrderItemDao orderItemDao;

    @Autowired
    private UserDao userDao;

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
        List<Order> allOrderList = orderDao.findByFinishTimeGreaterThan(new Date(startTime));

        return splitOrderByChef(allOrderList);
    }

    private List<List<Order>> getOrderListOfAllChefByRange(OrderDao orderDao, long start, long end) {
        List<Order> allOrderList = orderDao.findByFinishTimeGreaterThanAndStartPrepareTimeLessThanEqual(
                new Date(start), new Date(end));

        return splitOrderByChef(allOrderList);
    }

    private List<List<Order>> splitOrderByChef(List<Order> allOrderList) {
        List<List<Order>> orderListOfAllChef = new ArrayList<>();
        List<Order> order0 = new ArrayList<>();
        List<Order> order1 = new ArrayList<>();
        List<Order> order2 = new ArrayList<>();
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
        // check if it is in open hours
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
    public @ResponseBody
    BaseResultTO submit(@RequestBody SubmitOrderTO submitOrderTO,
                        HttpSession httpSession) {
//        User user = (User)httpSession.getAttribute("USER");
        Date orderTime = Calendar.getInstance().getTime();

        if (submitOrderTO.getPickupTime() - orderTime.getTime() > MAX_TIME) {
            return new BaseResultTO(1, "Can not exceed 30 days");
        }

        List<OrderTO> orderTOList = submitOrderTO.getOrderTOList();
        int totalTime = calculateTotalTime(orderTOList);
        LocalDateTime idealEarliestStartTime = timestampToLocalDateTime(submitOrderTO.getPickupTime())
                .minusHours(1).minusSeconds(totalTime / 1000);

        List<List<Order>> orderListOfAllChef = getOrderListOfAllChefByRange(orderDao,
                localDateTimeToTimeStamp(idealEarliestStartTime),
                submitOrderTO.getPickupTime());

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
        long pickupTime = earliestStartTime + totalTime;
        if (pickupTime > submitOrderTO.getPickupTime()) {
            return new BaseResultTO(1, "We are full of orders. Please select another pickup time");
        }

        logger.debug("submit chefId: {}, earliestStartTime: {}, totalTime: {}, earliestPickupTime: {}", chefId, earliestStartTime,
                totalTime, pickupTime);
        logger.debug("submit echefId: {}, arliestStartTime: {}, totalTime: {}, earliestPickupTime: {}", chefId,
                timestampToString(earliestStartTime),
                totalTime, timestampToString(pickupTime));

        double totalPrice = 0.0;
        for (OrderTO orderTO : orderTOList) {
            totalPrice += menuItemDao.findOne(orderTO.getMenuId()).getUnitPrice() * orderTO.getCount();
        }
        List<OrderItem> orderItemList = new ArrayList<>();
        Order order = new Order(new Date(pickupTime), orderTime, totalPrice, totalTime, orderItemList, null, chefId,
                new Date(earliestStartTime), new Date(earliestStartTime + totalTime));
        orderDao.save(order);

        for (OrderTO orderTO : orderTOList) {
            MenuItem menuItem = menuItemDao.findOne(orderTO.getMenuId());
            OrderItem orderItem = new OrderItem(new Date(pickupTime), orderTime, null, order, menuItem,
                    new BigDecimal(menuItem.getUnitPrice()), menuItem.getPreparationTime() * orderTO.getCount(),
                    orderTO.getCount());
            orderItemDao.save(orderItem);
        }

        return new BaseResultTO(0, "We've received your order. Have a nice day :)");
    }


    @RequestMapping(value = "/getOrderHistory", method = RequestMethod.GET)
    public
    @ResponseBody
    List<OrderHistory> orderHistory() {
//        User user = (User)httpSession.getAttribute("USER");
        User user = userDao.findOne(1L);
        List<Order> orderList = orderDao.findByUser(user);

        List<OrderHistory> res = new ArrayList<>();
        for (Order order : orderList) {
            List<ItemAndCount> itemAndCountList = new ArrayList<>();
            for (OrderItem orderItem : order.getItemList()) {
                ItemAndCount itemAndCount = new ItemAndCount(orderItem.getItem().getName(), orderItem.getCount());
                itemAndCountList.add(itemAndCount);
            }
            int status = 0;
            long now = Instant.now().toEpochMilli();
            long startTime = order.getStartPrepareTime().getTime();
            long finishTime = order.getFinishTime().getTime();
            if (now < startTime) {
                startTime = 0;
            } else if (now >= startTime && now <= finishTime) {
                status = 1;
            } else {
                status = 2;
            }
            OrderHistory orderHistory = new OrderHistory(order.getId(), itemAndCountList, order.getTotalPrice(),
                    order.getPickUpTime(), status);
            res.add(orderHistory);
        }

        return res;
    }

    @RequestMapping(value = "/cancel", method = RequestMethod.POST)
    public
    @ResponseBody
    BaseResultTO cancelOrder(@RequestBody Long orderId, HttpSession httpSession) {
        User user = (User)httpSession.getAttribute("USER");
        logger.debug("Delete OrderId: {}", orderId);
        Order order = orderDao.findOne(orderId);
        if (order == null) {
            return new BaseResultTO(1, "Can find this order.");
        }
        long now = Instant.now().toEpochMilli();
        if (order.getStartPrepareTime().getTime() <= now) {
            return new BaseResultTO(1, "Order is in progress, cann't be canceled.");
        }
        List<OrderItem> orderItemList = order.getItemList();
        orderItemDao.delete(orderItemList);
        orderDao.delete(order);
        return new BaseResultTO(0, "Your order is canceled");
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

    static class BaseResultTO {
        int code;
        String message;

        public BaseResultTO() {
        }

        public BaseResultTO(int code, String message) {
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

    static class ItemAndCount {
        String itemName;
        int count;

        public ItemAndCount(String itemName, int count) {
            this.itemName = itemName;
            this.count = count;
        }

        public String getItemName() {
            return itemName;
        }

        public void setItemName(String itemName) {
            this.itemName = itemName;
        }

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }
    }

    static class OrderHistory {
        long orderId;
        List<ItemAndCount> itemAndCount;
        double totalPrice;
        Date pickupTime;

        /**
         * 0 not start, 1 processing,  2 done
         */
        int status;


        public OrderHistory(long orderId, List<OrderController.ItemAndCount> itemAndCount,
                            double totalPrice, Date pickupTime, int status) {
            this.orderId = orderId;
            this.itemAndCount = itemAndCount;
            this.totalPrice = totalPrice;
            this.pickupTime = pickupTime;
            this.status = status;
        }

        public long getOrderId() {
            return orderId;
        }

        public void setOrderId(long orderId) {
            this.orderId = orderId;
        }

        public List<OrderController.ItemAndCount> getItemAndCount() {
            return itemAndCount;
        }

        public void setItemAndCount(List<OrderController.ItemAndCount> itemAndCount) {
            this.itemAndCount = itemAndCount;
        }

        public double getTotalPrice() {
            return totalPrice;
        }

        public void setTotalPrice(double totalPrice) {
            this.totalPrice = totalPrice;
        }

        public Date getPickupTime() {
            return pickupTime;
        }

        public void setPickupTime(Date pickupTime) {
            this.pickupTime = pickupTime;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }
    }

}
