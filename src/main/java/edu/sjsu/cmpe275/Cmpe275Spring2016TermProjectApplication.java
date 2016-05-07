package edu.sjsu.cmpe275;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Cmpe275Spring2016TermProjectApplication {

    private static final Logger logger = LoggerFactory.getLogger(Cmpe275Spring2016TermProjectApplication.class);
	public static void main(String[] args) {
		SpringApplication.run(Cmpe275Spring2016TermProjectApplication.class, args);
	}


//    @Bean
//    public CommandLineRunner insertOrderData(OrderDao orderDao, UserDao userDao, OrderItemDao orderItemDao) {
//        return (args) -> {
//            Date pickupTime  = Calendar.getInstance().getTime();
//            Date orderTime = pickupTime;
//
//            User user = new User(
//                    "a@a.com",
//                    "password",
//                    true,
//                    "User",
//                    "123"
//            );
//            userDao.save(user);
//
//            MenuItem item = new MenuItem();
//            BigDecimal price = BigDecimal.valueOf(1.0);
//
//            List<OrderItem> itemList = new ArrayList<>();
//            Date startPrepareTime = pickupTime;
//            Order order = new Order(
//                    pickupTime,
//                    orderTime,
//                    20.0,
//                    10,
//                    itemList,
//                    user,
//                    0,
//                    startPrepareTime
//            );
//            OrderItem orderItem = new OrderItem(
//                    pickupTime,
//                    orderTime,
//                    user,
//                    order,
//                    item,
//                    price,
//                    10
//                    );
//
//            itemList.add(orderItem);
//            orderItemDao.save(orderItem);
//            orderDao.save(order);
//
//        };
//    }
}
