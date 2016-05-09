package edu.sjsu.cmpe275;

import edu.sjsu.cmpe275.dao.MenuItemDao;
import edu.sjsu.cmpe275.dao.OrderDao;
import edu.sjsu.cmpe275.dao.OrderItemDao;
import edu.sjsu.cmpe275.dao.UserDao;
import edu.sjsu.cmpe275.domain.MenuItem;
import edu.sjsu.cmpe275.domain.Order;
import edu.sjsu.cmpe275.domain.OrderItem;
import edu.sjsu.cmpe275.domain.User;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import edu.sjsu.cmpe275.domain.MenuItem;
import edu.sjsu.cmpe275.domain.Order;

@SpringBootApplication
public class Cmpe275Spring2016TermProjectApplication {

    private static final Logger logger = LoggerFactory.getLogger(Cmpe275Spring2016TermProjectApplication.class);
	public static void main(String[] args) {
		SpringApplication.run(Cmpe275Spring2016TermProjectApplication.class, args);
	}


//    @Bean
    public CommandLineRunner insertOrderData(OrderDao orderDao, UserDao userDao, OrderItemDao orderItemDao,
                                             MenuItemDao menuItemDao) {
        return (args) -> {
//            addData(orderDao, userDao, orderItemDao, menuItemDao);
//            addFinishTime(orderDao);
        };
    }

    private static void addFinishTime(OrderDao orderDao) {
        for (Order order : orderDao.findAll()) {
            Date startTime = order.getStartPrepareTime();
            Date finishTime = new Date(startTime.getTime() + order.getTotalTime());
            order.setFinishTime(finishTime);
            orderDao.save(order);
        }
    }

    private static void addData(OrderDao orderDao, UserDao userDao, OrderItemDao orderItemDao,
                                MenuItemDao menuItemDao) {
        Date pickupTime  = Calendar.getInstance().getTime();
        Date orderTime = pickupTime;

        User user = new User(
                "a@a.com",
                "password",
                true,
                "123"
        );
        userDao.save(user);

        MenuItem item = new MenuItem((byte)1, "Food", "dfaf", 1.0, 1, 1);
        menuItemDao.save(item);

        BigDecimal price = BigDecimal.valueOf(1.0);

        List<OrderItem> itemList = new ArrayList<>();
        Date startPrepareTime = pickupTime;
        int totalTime = 10;
        Date finishTime = new Date(startPrepareTime.getTime() + totalTime);
        Order order = new Order(
                pickupTime,
                orderTime,
                20.0,
                totalTime,
                itemList,
                user,
                0,
                startPrepareTime,
                finishTime
        );
        OrderItem orderItem = new OrderItem(
                pickupTime,
                orderTime,
                user,
                order,
                item,
                price,
                10,
                1
        );
        OrderItem orderItem1 = new OrderItem(
                pickupTime,
                orderTime,
                user,
                order,
                item,
                price,
                10,
                1
        );

        itemList.add(orderItem);
        orderDao.save(order);
        orderItemDao.save(orderItem);
        orderItemDao.save(orderItem1);
    }
}
