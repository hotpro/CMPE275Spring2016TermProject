package edu.sjsu.cmpe275.controller;

import edu.sjsu.cmpe275.domain.MenuItem;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lee on 5/5/16.
 */
@RequestMapping("/menu")
public class MenuController {
    @RequestMapping(method = RequestMethod.GET)
    public @ResponseBody
    List<MenuItem> getMenuItems() {
        List<MenuItem> list = new ArrayList<>();
        MenuItem item1 = new MenuItem();
        item1.setName("wang lao ji");
        item1.setUnitPrice(3.00);
        item1.setPictureURL("image/wanglaoji.jpg");
        item1.setCalories(100);
        item1.setId(1L);

        MenuItem item2 = new MenuItem();
        item2.setName("pepsi");
        item2.setUnitPrice(3.00);
        item2.setPictureURL("image/pepsi.jpg");
        item2.setCalories(200);
        item2.setId(2L);
        list.add(item1);
        list.add(item2);
        return list;
    }
}