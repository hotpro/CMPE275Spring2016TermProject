package edu.sjsu.cmpe275.dao;

import edu.sjsu.cmpe275.domain.Order;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by yutao on 5/5/16.
 */
public interface OrderDao extends CrudRepository<Order, Long> {
}
