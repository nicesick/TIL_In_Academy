package com.product;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;

import com.frame.Dao;
import com.mapper.ProductMapper;
import com.vo.Product;

public class ProductDao implements Dao<Integer, Product> {
	@Autowired
	private ProductMapper pm;
	
	@Override
	public void insert(Product v) throws Exception {
		pm.insert(v);
	}

	@Override
	public void update(Integer k, Product v) throws Exception {
		Product p = null;
		
		p = pm.select(k);
		
		if (p != null) {
			pm.update(v);
		}
	}

	@Override
	public void delete(Integer k) throws Exception {
		pm.delete(k);
	}

	@Override
	public Product select(Integer k) throws Exception {
		Product p = null;
		
		p = pm.select(k);
		
		return p;
	}

	@Override
	public ArrayList<Product> selectAll() throws Exception {
		ArrayList<Product> ps = null;
		
		ps = pm.selectall();
		return ps;
	}

}
