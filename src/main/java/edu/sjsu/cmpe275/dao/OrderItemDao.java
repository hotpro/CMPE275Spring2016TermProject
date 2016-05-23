package edu.sjsu.cmpe275.dao;

import edu.sjsu.cmpe275.domain.OrderItem;

import java.util.Date;
import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by yutao on 5/5/16.
 */
@Repository
public interface OrderItemDao extends CrudRepository<OrderItem, Long> {
	
	List<OrderItem> findByOrderTimeBetween(Date startTime, Date endTime);
	
}
