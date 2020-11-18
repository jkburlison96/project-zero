package com.car_dealership.repo;

import java.util.List;

public interface DaoContract<T, I> {
	List<T> findAll();

	T findById(I i);

	T update(T t);

	T create(T t);

	int delete(I i);
}
