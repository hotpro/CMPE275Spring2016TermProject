package edu.sjsu.cmpe275.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import edu.sjsu.cmpe275.domain.OrderItem;
import edu.sjsu.cmpe275.domain.OrderItemRating;
import edu.sjsu.cmpe275.domain.User;

public interface OrderItemRatingDao  extends CrudRepository<OrderItemRating, Long>{
	
	List<OrderItemRating> findByUserAndOrderItem(User user, OrderItem orderItem);
	
	List<OrderItemRating> findByUser(User user);
	
	List<OrderItemRating> findByOrderItem(OrderItem orderItem);
}
