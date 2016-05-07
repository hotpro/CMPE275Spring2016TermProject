package edu.sjsu.cmpe275.controller;

import edu.sjsu.cmpe275.dao.MenuItemDao;
import edu.sjsu.cmpe275.dao.OrderDao;
import edu.sjsu.cmpe275.domain.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.TimeZone;

/**
 * Created by yutao on 5/5/16.
 */
@Controller
@RequestMapping("/order")
public class OrderController {

    /** ONE_DAY_WORK is 15 hours long. unit is millisecond*/
    private static final long ONE_DAY_WORK = (21 - 6) * 60 * 60 * 1000;

    @Autowired
    private OrderDao orderDao;
    private MenuItemDao menuItemDao;

    @RequestMapping(value = "/getEarliestPickupTime", method = RequestMethod.GET)
    public @ResponseBody
    PickupTimeTO getEarliestPickupTime(@RequestBody List<OrderTO> orderTOList) {


        /*
        test case:
        1. empty order. 1. order before 6am. 2. order after 6am before 9pm. 3. order after 9pm
         */

        int totalTime = 0;
        for (OrderTO orderTO : orderTOList) {
            totalTime += menuItemDao.findOne(orderTO.getMenuId()).getPreparationTime();
        }

        // order take too long time
        if (totalTime > ONE_DAY_WORK) {
            // TODO:
        }

        List<Order> order0 = new ArrayList<>();
        List<Order> order1 = new ArrayList<>();
        List<Order> order2 = new ArrayList<>();
        for (Order order : orderDao.findAll()) {
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

        // TODO: need to consider the case that order start after 30 days
        long now = LocalDateTime.now().atZone(TimeZone.getDefault().toZoneId()).toInstant().toEpochMilli();
        long startTime0 = findEarliestStartTime(now, totalTime, order0);
        long startTime1 = findEarliestStartTime(now, totalTime, order1);
        long startTime2 = findEarliestStartTime(now, totalTime, order2);

        return new PickupTimeTO(Math.min(Math.min(startTime0, startTime1), startTime2) + totalTime, "");
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
    public @ResponseBody String submitOrder(@RequestBody List<OrderTO> orderTOList) {
        return "NO";
    }



    static class OrderTO {
        long menuId;
        int count;

        public OrderTO(long menuId, int count) {
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

    static class PickupTimeTO {
        public PickupTimeTO(long earliestPickupTime, String errorMsg) {
            this.earliestPickupTime = earliestPickupTime;
            this.errorMsg = errorMsg;
        }

        private long earliestPickupTime;
        private String errorMsg;
    }
}
