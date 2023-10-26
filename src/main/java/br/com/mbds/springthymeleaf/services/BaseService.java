package br.com.mbds.springthymeleaf.services;

import java.util.List;
import java.util.Optional;

public interface BaseService<T> {

	T save(T entity);

	void delete(Long id);

	Optional<T> findById(Long id);

	List<T> findAll();

}
