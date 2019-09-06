package com.product;

public class Product {
	String id;
	String name;
	Double price;
	String imgname;

	public Product(String id, String name, Double price, String imgname) {
		super();
		this.id = id;
		this.name = name;
		this.price = price;
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

	public String getImgname() {
		return imgname;
	}

	public void setImgname(String imgname) {
		this.imgname = imgname;
	}

	@Override
	public String toString() {
		return "Product [id=" + id + ", name=" + name + ", price=" + price + ", imgname=" + imgname + "]";
	}

}
