package com.vo;

import java.util.Date;

public class Product {
	String id;
	String name;
	Double price;
	Date date;
	String imgname;

	public Product(String id, String name, Double price, Date date, String imgname) {
		super();
		this.id = id;
		this.name = name;
		this.price = price;
		this.date = date;
		this.imgname = imgname;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getImgname() {
		return imgname;
	}

	public void setImgname(String imgname) {
		this.imgname = imgname;
	}

	@Override
	public String toString() {
		return "Product [id=" + id + ", name=" + name + ", price=" + price + ", date=" + date + ", imgname=" + imgname
				+ "]";
	}

}
