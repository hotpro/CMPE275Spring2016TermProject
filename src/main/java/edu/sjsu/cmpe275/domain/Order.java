package edu.sjsu.cmpe275.domain;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Order {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	private User user;
	
	private Date pickUpTime;
	
	private Date orderTime;
	
	private double totalPrice;
	
	private int totalTime;
	
	@OneToMany(mappedBy = "order")
	private List<OrderItem> itemList;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Date getPickUpTime() {
		return pickUpTime;
	}

	public void setPickUpTime(Date pickUpTime) {
		this.pickUpTime = pickUpTime;
	}

	public Date getOrderTime() {
		return orderTime;
	}

	public void setOrderTime(Date orderTime) {
		this.orderTime = orderTime;
	}


	public double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public int getTotalTime() {
		return totalTime;
	}

	public void setTotalTime(int totalTime) {
		this.totalTime = totalTime;
	}

	public List<OrderItem> getItemList() {
		return itemList;
	}

	public void setItemList(List<OrderItem> itemList) {
		this.itemList = itemList;
	}
	
}
