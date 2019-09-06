package com.user;

import com.frame.Dao;

public class UserMariaDao implements Dao {

	@Override
	public void insert() {
		System.out.println("USERMARIADAO - INSERT");
	}
}
