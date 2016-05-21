package edu.sjsu.cmpe275;

import edu.sjsu.cmpe275.dao.OrderDao;
import edu.sjsu.cmpe275.dao.UserDao;
import edu.sjsu.cmpe275.domain.Order;
import edu.sjsu.cmpe275.domain.User;
import edu.sjsu.cmpe275.service.MailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static com.sun.tools.doclint.Entity.or;

/**
 * Created by yutao on 5/20/16.
 */

@Component
public class ScheduledTasks {
    private static final Logger logger = LoggerFactory.getLogger(ScheduledTasks.class);

    @Autowired
    private UserDao userDao;

    @Autowired
    private OrderDao orderDao;

    @Autowired
    private MailService mailService;

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd yyyy HH:mm");

    @Scheduled(fixedRate = 5000)
    public void monitorOrderStatus() {
        logger.debug("monitorOrderStatus");
        long now = Calendar.getInstance().getTime().getTime();
        for (Order order : orderDao.findAll()) {
            long start = order.getStartPrepareTime().getTime();
            if (start <= now && start + 5000 >= now) {
                User user = order.getUser();
                String to = user.getEmail();
                Date finish = order.getFinishTime();
                String text = "order ready to pick up soon with predicted ready time: " + dateFormat.format(finish);
                mailService.send(to, "Order On Progress", text);
            }
        }
    }
}
