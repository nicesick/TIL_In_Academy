package com.mapper;

import java.util.ArrayList;

public interface SearchMapper<K, V> {
	public ArrayList<V> search(K k);
}
