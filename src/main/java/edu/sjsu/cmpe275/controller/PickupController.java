package edu.sjsu.cmpe275.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.LocalDateTime;

/**
 * Created by yutao on 5/5/16.
 */
@Controller
@RequestMapping("/pickup")
public class PickupController {

    @RequestMapping(method = RequestMethod.GET)
    public @ResponseBody PickupTime getPickupTime() {
        LocalDateTime now = LocalDateTime.now();
        return new PickupTime(1, now.getNano() / 1000);
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
