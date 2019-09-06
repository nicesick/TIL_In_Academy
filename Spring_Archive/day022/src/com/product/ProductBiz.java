package com.product;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import com.frame.Biz;
import com.frame.Dao;

@Service("productbiz")
public class ProductBiz implements Biz<String, Product> {
	
	@Autowired
	Dao<String, Product> dao;
	
	public ProductBiz() {
		System.out.println("productBiz start....");
	}
	
	@Override
	public void insert(Product v) throws Exception {
		dao.insert(v);
	}

	@Override
	public void update(Product v) throws Exception {
		dao.update(v);
	}

	@Override
	public void delete(Product v) throws Exception {
		dao.delete(v);
	}

	@Override
	public Product select(String k) throws Exception {
		return dao.select(k);
	}

	@Override
	public ArrayList<Product> selectAll() throws Exception {
		return dao.selectAll();
	}

}
