package com.user;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;

import com.frame.Dao;
import com.frame.Search;
import com.mapper.SearchMapper;
import com.mapper.UserMapper;
import com.vo.User;

public class UserDao implements Dao<String, User>, Search<String, User> {
	
	@Autowired
	private UserMapper um;
	
	@Autowired
	private SearchMapper<String, User> sm;
	
	@Override
	public void insert(User v) throws Exception {
		um.insert(v);
	}

	@Override
	public void update(String k, User v) throws Exception {
		User user = null;
		user = um.select(k);
		
		if (user != null) {
			user.setName(v.getName());
			user.setPwd(v.getPwd());
			
			um.update(user);
		}
	}

	@Override
	public void delete(String k) throws Exception {
		um.delete(k);
	}

	@Override
	public User select(String k) throws Exception {
		User user = null;
		
		user = um.select(k);
		return user;
	}

	@Override
	public ArrayList<User> selectAll() throws Exception {
		ArrayList<User> users = new ArrayList<User>();
		
		users = um.selectall();
		
		return users;
	}

	@Override
	public ArrayList<User> search(String k) {
		ArrayList<User> users = sm.search(k);
		
		return users;
	}

}
