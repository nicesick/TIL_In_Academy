package com.frame;

import java.util.ArrayList;

public interface Dao<K, V> {
	public abstract void insert(V v) throws Exception;
	public abstract void update(K k, V v) throws Exception;
	public abstract void delete(V v) throws Exception;
	public abstract V select(K k) throws Exception;
	public abstract ArrayList<V> selectAll() throws Exception;
}
