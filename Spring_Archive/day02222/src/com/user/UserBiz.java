package com.user;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.frame.Biz;
import com.frame.Dao;
import com.vo.User;

@Service("userbiz")
public class UserBiz implements Biz<String, User> {
	
	@Autowired
	private Dao<String, User> dao;
	
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
	public void delete(User v) throws Exception {
		System.out.println("USERBIZ : DELETE");
		dao.delete(v);
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

}
