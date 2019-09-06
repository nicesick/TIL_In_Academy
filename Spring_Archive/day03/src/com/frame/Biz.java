package com.frame;

import java.util.ArrayList;

import org.springframework.transaction.annotation.Transactional;

public interface Biz<K, V> {
	@Transactional
	public abstract void insert(V v) throws Exception;
	@Transactional
	public abstract void update(K k, V v) throws Exception;
	@Transactional
	public abstract void delete(K k) throws Exception;
	
	public abstract V select(K k) throws Exception;
	public abstract ArrayList<V> selectAll() throws Exception;
}
