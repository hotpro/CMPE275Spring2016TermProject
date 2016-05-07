package edu.sjsu.cmpe275.domain;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "user_order")
public class Order {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "pickUpTime")
	private Date pickUpTime;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "orderTime")
	private Date orderTime;
	
	private double totalPrice;
	
	private int totalTime;

	@OneToMany(mappedBy = "order", fetch = FetchType.LAZY)
	private List<OrderItem> itemList;

	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;

    private int chiefId;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "startPrepareTime")
    private Date startPrepareTime;

    public Order() {
    }

    public Order(Date pickUpTime, Date orderTime, double totalPrice, int totalTime, List<OrderItem> itemList, User user, int chiefId, Date startPrepareTime) {
        this.pickUpTime = pickUpTime;
        this.orderTime = orderTime;
        this.totalPrice = totalPrice;
        this.totalTime = totalTime;
        this.itemList = itemList;
        this.user = user;
        this.chiefId = chiefId;
        this.startPrepareTime = startPrepareTime;
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

    public int getChiefId() {
        return chiefId;
    }

    public void setChiefId(int chiefId) {
        this.chiefId = chiefId;
    }

    public Date getStartPrepareTime() {
        return startPrepareTime;
    }

    public void setStartPrepareTime(Date startPrepareTime) {
        this.startPrepareTime = startPrepareTime;
    }
}
