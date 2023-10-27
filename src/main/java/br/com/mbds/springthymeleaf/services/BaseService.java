package br.com.mbds.springthymeleaf.services;

import java.util.List;

public interface BaseService<T> {

	T save(T entity);

	void delete(Long id);

	T findById(Long id);

	List<T> findAll();

}
