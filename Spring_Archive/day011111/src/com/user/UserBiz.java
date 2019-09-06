package com.user;

import java.util.ArrayList;

import com.frame.Biz;
import com.frame.Dao;

public class UserBiz implements Biz<String, User> {
	Dao<String, User> dao;

	public void setDao(Dao<String, User> dao) {
		this.dao = dao;
	}

	@Override
	public void insert(User v) throws Exception {
		dao.insert(v);
	}

	@Override
	public void update(User v) throws Exception {
		dao.update(v);
	}

	@Override
	public void delete(User v) throws Exception {
		dao.delete(v);
	}

	@Override
	public User select(String k) throws Exception {
		return dao.select(k);
	}

	@Override
	public ArrayList<User> selectAll() throws Exception {
		return dao.selectAll();
	}

	public void startBiz() {
		System.out.println("šë°¡°®°¡ÀÛÀÚ°®");
	}

	public void endBiz() {
		System.out.println("³¡³¡³¡³¡²ôºjºj");
	}
}
