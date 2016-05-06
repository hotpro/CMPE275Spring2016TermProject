package edu.sjsu.cmpe275.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.LocalDateTime;

/**
 * Created by yutao on 5/5/16.
 */
@Controller
@RequestMapping("/order")
public class OrderController {

    @RequestMapping(method = RequestMethod.GET)
    public @ResponseBody PickupTime getEarliestPickupTime() {
        LocalDateTime now = LocalDateTime.now();
        return new PickupTime(1, now.getNano() / 1000);
    }

    @RequestMapping(value = "/submit", method = RequestMethod.POST)
    public @ResponseBody String submitOrder(@RequestBody OrderVO orderVO) {
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
