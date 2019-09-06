package com.user;

import java.util.ArrayList;

import com.frame.Dao;

public class UserOracleDao implements Dao<String, User> {

	@Override
	public void insert(User v) throws Exception {
		System.out.println(v + "UserOracleDao Inserted...");
	}

	@Override
	public void update(User v) throws Exception {
		System.out.println(v + "UserOracleDao Updated...");
	}

	@Override
	public void delete(User v) throws Exception {
		System.out.println(v + "UserOracleDao Deleted...");
	}

	@Override
	public User select(String k) throws Exception {
		return new User("ÁöÈÆ","Â¯Â¯","123123");
	}

	@Override
	public ArrayList<User> selectAll() throws Exception {
		ArrayList<User> list = new ArrayList<User>();
		
		list.add(new User("ÁöÈÆ","Â¯Â¯","1231234"));
		list.add(new User("ÁöÈÆ","Â¯Â¯","12312345"));
		list.add(new User("ÁöÈÆ","Â¯Â¯","123123456"));
		list.add(new User("ÁöÈÆ","Â¯Â¯","1231234567"));
		
		return list;
	}

}
