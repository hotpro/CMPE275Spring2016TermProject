package edu.sjsu.cmpe275.service;

import edu.sjsu.cmpe275.domain.Order;

/**
 * Created by yutao on 5/5/16.
 */
public interface OrderService {

    public Order submitOrder(Order order);

    public long getEarliestPickupTime(int totalTime);
}
