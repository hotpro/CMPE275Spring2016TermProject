package edu.sjsu.cmpe275.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by yutao on 5/5/16.
 */
@Controller
@RequestMapping("/order")
public class OrderController {

    @RequestMapping(value = "/getEarliestPickupTime", method = RequestMethod.GET)
    public @ResponseBody PickupTime getEarliestPickupTime(@RequestBody List<OrderVO> orderVOList) {
        LocalDateTime now = LocalDateTime.now();
        return new PickupTime(1, now.getNano() / 1000);
    }

    @RequestMapping(value = "/submit", method = RequestMethod.POST)
    public @ResponseBody String submitOrder(@RequestBody List<OrderVO> orderVOList) {
        return "";
    }

    static class OrderVO {
        long id;
        int count;

        public OrderVO(long id, int count) {
            this.id = id;
            this.count = count;
        }
    }

    static class PickupTime {
        public PickupTime(long id, long earliestPickupTime) {
            this.id = id;
            this.earliestPickupTime = earliestPickupTime;
        }

        long id;
        long earliestPickupTime;
    }
}
