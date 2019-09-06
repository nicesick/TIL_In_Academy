package com.user;

import com.frame.Dao;

public class UserOracleDao implements Dao {

	@Override
	public void insert() {
		System.out.println("USERORACLEDAO - INSERT");
	}
}
