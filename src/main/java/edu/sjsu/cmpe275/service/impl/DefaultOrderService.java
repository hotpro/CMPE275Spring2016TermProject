package edu.sjsu.cmpe275.service.impl;

import edu.sjsu.cmpe275.domain.Order;
import edu.sjsu.cmpe275.service.OrderService;

/**
 * Created by yutao on 5/5/16.
 */
public class DefaultOrderService implements OrderService {
    @Override
    public Order submitOrder(Order order) {
        return null;
    }

    @Override
    public long getEarliestTime(int totalTime) {
        return 0;
    }
}
