package edu.sjsu.cmpe275.controller;

import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by yutao on 5/5/16.
 */
@RequestMapping("/user")
public class UserController {

    @RequestMapping("/signupform")
    public String signupForm() {
        return "signupform";
    }

    @RequestMapping("signup")
    public void signup() {

    }

    public void active() {

    }
}
