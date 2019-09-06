package com.product;

import java.util.ArrayList;

import com.frame.Dao;

public class ProductOracleDao implements Dao<String, Product> {

	@Override
	public void insert(Product v) throws Exception {
		System.out.println(v + "ProductOracleDao Inserted...");
	}

	@Override
	public void update(Product v) throws Exception {
		System.out.println(v + "ProductOracleDao Updated...");
	}

	@Override
	public void delete(Product v) throws Exception {
		System.out.println(v + "ProductOracleDao Deleted...");
	}

	@Override
	public Product select(String k) throws Exception {
		Product p = new Product("強","張",10000d,"戚");
		return p;
	}

	@Override
	public ArrayList<Product> selectAll() throws Exception {
		ArrayList<Product> ps = new ArrayList<Product>();
		
		ps.add(new Product("強","張",10000d,"戚"));
		ps.add(new Product("強","張",10000d,"戚"));
		ps.add(new Product("強","張",10000d,"戚"));
		ps.add(new Product("強","張",10000d,"戚"));
		ps.add(new Product("強","張",10000d,"戚"));
		
		return ps;
	}

}
