package edu.sjsu.cmpe275.domain;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
public class OrderItem {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "pickUpTime")
	private Date pickUpTime;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "orderTime")
	private Date orderTime;
	
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;
	
	@ManyToOne
	@JoinColumn(name = "order_id")
	private Order order;
	
	@ManyToOne
	@JoinColumn(name = "item_id")
	private MenuItem item;
	
	
	private BigDecimal price;
	
	private int totalTime;
	
	private int count;

    public OrderItem() {
    }

    public OrderItem(Date pickUpTime, Date orderTime, User user, Order order, MenuItem item, BigDecimal price,
					 int totalTime, int count) {
        this.pickUpTime = pickUpTime;
        this.orderTime = orderTime;
        this.user = user;
        this.order = order;
        this.item = item;
        this.price = price;
        this.totalTime = totalTime;
		this.count = count;
	}

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

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public int getTotalTime() {
		return totalTime;
	}

	public void setTotalTime(int totalTime) {
		this.totalTime = totalTime;
	}

	public MenuItem getItem() {
		return item;
	}

	public void setItem(MenuItem item) {
		this.item = item;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}
	
}