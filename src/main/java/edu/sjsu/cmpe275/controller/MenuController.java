package edu.sjsu.cmpe275.controller;

import edu.sjsu.cmpe275.dao.MenuItemDao;
import edu.sjsu.cmpe275.domain.MenuItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

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
    List<MenuItem> getMenuItems() {
        //get MenuItem from database then return the list
        //List<MenuItem> list = menuItemDao.findAll();

        List<MenuItem> list = new ArrayList<>();
        MenuItem item1 = new MenuItem();
        item1.setName("wang lao ji");
        item1.setUnitPrice(3.00);
        item1.setPictureURL("image/wanglaoji.jpg");
        item1.setCategory((byte)0);
        item1.setCalories(100);
        item1.setId(1L);

        MenuItem item2 = new MenuItem();
        item2.setName("pepsi");
        item2.setUnitPrice(3.00);
        item2.setPictureURL("image/pepsi.png");
        item2.setCategory((byte)2);
        item2.setCalories(200);
        item2.setId(2L);


        MenuItem item3 = new MenuItem();
        item3.setName("wang lao ji");
        item3.setUnitPrice(3.00);
        item3.setPictureURL("image/wanglaoji.jpg");
        item3.setCategory((byte)2);
        item3.setCalories(100);
        item3.setId(1L);

        MenuItem item4 = new MenuItem();
        item4.setName("wang lao ji");
        item4.setUnitPrice(3.00);
        item4.setPictureURL("image/wanglaoji.jpg");
        item4.setCategory((byte)1);
        item4.setCalories(100);
        item4.setId(1L);

        list.add(item1);
        list.add(item2);
        list.add(item3);
        list.add(item4);
        return list;
    }
//
//    @RequestMapping(value="/preCheckout", method = RequestMethod.GET)
//    public  @ResponseBody String comfirmOrder(@RequestBody List<Order> order)

}