package com.user;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.frame.Biz;
import com.frame.Dao;
import com.frame.Search;
import com.vo.User;

@Service("userbiz")
public class UserBiz implements Biz<String, User>, Search<String, User> {
	
	@Autowired
	private Dao<String, User> dao;
	
	@Autowired
	private Search<String, User> search;
	
	@Override
	public void insert(User v) throws Exception {
		System.out.println("USERBIZ : INSERT");
		dao.insert(v);
	}

	@Override
	public void update(String k, User v) throws Exception {
		System.out.println("USERBIZ : UPDATE");
		dao.update(k, v);
	}

	@Override
	public void delete(String k) throws Exception {
		System.out.println("USERBIZ : DELETE");
		dao.delete(k);
	}

	@Override
	public User select(String k) throws Exception {
		System.out.println("USERBIZ : SELECT");
		User user = dao.select(k);
		
		return user;
	}

	@Override
	public ArrayList<User> selectAll() throws Exception {
		System.out.println("USERBIZ : SELECTALL");
		
		ArrayList<User> users = dao.selectAll();
		
		return users;
	}

	@Override
	public ArrayList<User> search(String k) {
		System.out.println("USERBIZ : SEARCH");
		
		ArrayList<User> users = search.search(k);
		return users;
	}

}
