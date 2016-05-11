package edu.sjsu.cmpe275.controller;

import edu.sjsu.cmpe275.dao.MenuItemDao;
import edu.sjsu.cmpe275.domain.MenuItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import static javafx.scene.input.KeyCode.R;

/**
 * Created by Lee on 5/5/16.
 */
@Controller
@RequestMapping("/menu")
public class MenuController {

    @Autowired
    private MenuItemDao menuItemDao;

    @RequestMapping(method = RequestMethod.GET)
    public @ResponseBody
    Iterable<MenuItem> getMenuItems() {
        //get MenuItem from database then return the list
        Iterable<MenuItem> list = this.menuItemDao.findAll();

        return list;
    }

    @RequestMapping(value = "/orderHistory", method = RequestMethod.GET)
    public String getOrderHistory() {
        return "orderHistory";
    }
//
//    @RequestMapping(value="/preCheckout", method = RequestMethod.GET)
//    public  @ResponseBody String comfirmOrder(@RequestBody List<Order> order)

    @RequestMapping(value = "/submitRating", method = RequestMethod.POST)
    public OrderController.BaseResultTO submitRating() {
        return new OrderController.BaseResultTO(1, "");
    }

    public static class UserMenuRating {
        long menuItemId;
        int rating;
    }


}