package br.com.mbds.springthymeleaf.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BaseService<T> {

	T save(T entity);

	void delete(Long id);

	T findById(Long id);

	List<T> findAll();

	Page<T> findAll(Pageable pageable);

}
