package com.user;

import com.frame.Biz;
import com.frame.Dao;

public class UserBiz implements Biz {
	private Dao dao;

	public Dao getDao() {
		return dao;
	}

	public void setDao(Dao dao) {
		this.dao = dao;
	}

	@Override
	public void register() {
		System.out.println("USERBIZ - REGISTER");
		dao.insert();
	}
}
