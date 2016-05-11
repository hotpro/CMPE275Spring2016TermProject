package edu.sjsu.cmpe275.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/")
public class HelloController {

    @RequestMapping("/")
    public String index() {
        return "hello";
    }

    @RequestMapping("/signin")
    public String loginView() {
        return "login";
    }
    
    @RequestMapping("/admin")
    public String admin() {
        return "dashboard";
    }

    @RequestMapping(value = "/signupform", method = RequestMethod.GET)
    public String signupForm() {
        return "signupform";
    }

    @RequestMapping("/locations")
    public String location() {
        return "locations";
    }
}
