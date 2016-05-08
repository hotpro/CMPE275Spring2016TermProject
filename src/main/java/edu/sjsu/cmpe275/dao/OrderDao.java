package edu.sjsu.cmpe275.dao;

import edu.sjsu.cmpe275.domain.Order;
import org.springframework.data.repository.CrudRepository;

import java.util.Date;
import java.util.List;

/**
 * Created by yutao on 5/5/16.
 */
public interface OrderDao extends CrudRepository<Order, Long> {
    List<Order> findByFinishTimeGreaterThanEqual(Date date);

}
