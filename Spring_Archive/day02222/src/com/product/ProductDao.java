package com.product;

import java.util.ArrayList;
import java.util.Date;

import com.frame.Dao;
import com.vo.Product;

public class ProductDao implements Dao<String, Product> {

	@Override
	public void insert(Product v) throws Exception {
		System.out.println("ProductDAO : INSERT");
		System.out.println(v);
	}

	@Override
	public void update(String k, Product v) throws Exception {
		System.out.println("ProductDAO : UPDATE");
		System.out.println(k);
		System.out.println(v);
	}

	@Override
	public void delete(Product v) throws Exception {
		System.out.println("ProductDAO : DELETE");
		System.out.println(v);
	}

	@Override
	public Product select(String k) throws Exception {
		System.out.println("ProductDAO : SELECT");
		System.out.println(k);
		
		Product p = new Product(k,"ÁöÈÆ",100000d, new Date(),"¶¯Ä¥");
		
		return p;
	}

	@Override
	public ArrayList<Product> selectAll() throws Exception {
		System.out.println("ProductDAO : SELECTALL");
		
		ArrayList<Product> ps = new ArrayList<Product>();
		
		ps.add(new Product("1","ÁöÈÆ",100000d, new Date(),"¶¯Ä¥"));
		ps.add(new Product("2","ÁöÈÆ",100000d, new Date(),"¶¯Ä¥"));
		ps.add(new Product("3","ÁöÈÆ",100000d, new Date(),"¶¯Ä¥"));
		ps.add(new Product("4","ÁöÈÆ",100000d, new Date(),"¶¯Ä¥"));
		ps.add(new Product("5","ÁöÈÆ",100000d, new Date(),"¶¯Ä¥"));
		
		return ps;
	}

}
