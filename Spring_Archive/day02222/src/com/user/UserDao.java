package com.user;

import java.util.ArrayList;

import com.frame.Dao;
import com.vo.User;

public class UserDao implements Dao<String, User> {

	@Override
	public void insert(User v) throws Exception {
		System.out.println("USERDAO : INSERT");
		System.out.println(v);
	}

	@Override
	public void update(String k, User v) throws Exception {
		System.out.println("USERDAO : UPDATE");
		System.out.println(k);
		System.out.println(v);
	}

	@Override
	public void delete(User v) throws Exception {
		System.out.println("USERDAO : DELETE");
		System.out.println(v);
	}

	@Override
	public User select(String k) throws Exception {
		System.out.println("USERDAO : SELECT");
		System.out.println(k);
		
		User user = new User(k,"ÁöÈÆ","¶¯Ä¥");
		return user;
	}

	@Override
	public ArrayList<User> selectAll() throws Exception {
		System.out.println("USERDAO : SELECTALL");
		
		ArrayList<User> users = new ArrayList<User>();
		
		users.add(new User("1","ÁöÈÆ","¶¯Ä¥"));
		users.add(new User("2","ÁöÈÆ","¶¯Ä¥"));
		users.add(new User("3","ÁöÈÆ","¶¯Ä¥"));
		users.add(new User("4","ÁöÈÆ","¶¯Ä¥"));
		users.add(new User("5","ÁöÈÆ","¶¯Ä¥"));
		
		return users;
	}

}
