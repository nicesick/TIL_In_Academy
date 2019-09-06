package com.product;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.frame.Biz;
import com.frame.Dao;
import com.vo.Product;

@Service("productbiz")
public class ProductBiz implements Biz<Integer, Product> {
	    
	@Autowired
	private Dao<Integer, Product> dao;
	
	@Override
	public void insert(Product v) throws Exception {
		System.out.println("ProductBIZ : INSERT");
		dao.insert(v);
	}

	@Override
	public void update(Integer k, Product v) throws Exception {
		System.out.println("ProductBIZ : UPDATE");
		dao.update(k, v);
	}

	@Override
	public void delete(Integer k) throws Exception {
		System.out.println("ProductBIZ : DELETE");
		dao.delete(k);
	}

	@Override
	public Product select(Integer k) throws Exception {
		System.out.println("ProductBIZ : SELECT");
		Product p = dao.select(k);
		
		return p;
	}

	@Override
	public ArrayList<Product> selectAll() throws Exception {
		System.out.println("ProductBIZ : SELECTALL");
		
		ArrayList<Product> ps = dao.selectAll();
		
		return ps;
	}
}
