package edu.sjsu.cmpe275.domain;

import java.math.BigDecimal;

public class MenuItem {
	
	private Long id;
	
	private byte category;
	
	private String name;
	
	private String pictureDir;
	
	private BigDecimal unitPrice;
	
	private int calories;
	
	private int preparationTime;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public byte getCategory() {
		return category;
	}

	public void setCategory(byte category) {
		this.category = category;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPictureDir() {
		return pictureDir;
	}

	public void setPictureDir(String pictureDir) {
		this.pictureDir = pictureDir;
	}

	public BigDecimal getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(BigDecimal unitPrice) {
		this.unitPrice = unitPrice;
	}

	public int getCalories() {
		return calories;
	}

	public void setCalories(int calories) {
		this.calories = calories;
	}

	public int getPreparationTime() {
		return preparationTime;
	}

	public void setPreparationTime(int preparationTime) {
		this.preparationTime = preparationTime;
	}

}
