package edu.sjsu.cmpe275.service.impl;

import edu.sjsu.cmpe275.domain.Order;
import edu.sjsu.cmpe275.service.OrderService;
import org.springframework.stereotype.Service;

/**
 * Created by yutao on 5/5/16.
 */
@Service
public class OrderServiceImpl implements OrderService {
    @Override
    public Order submitOrder(Order order) {
        return null;
    }

    @Override
    public long getEarliestPickupTime(int totalTime) {
        return 0;
    }
}
