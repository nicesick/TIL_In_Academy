package com.user;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

import com.frame.Biz;
import com.frame.Dao;
import com.frame.LogAdvice;

@Lazy
@Repository("userbiz")
public class UserBiz implements Biz<String, User> {
	
	@Autowired
	Dao<String, User> dao;

	public UserBiz() {
		System.out.println("UserBiz start....");
		
	}

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
