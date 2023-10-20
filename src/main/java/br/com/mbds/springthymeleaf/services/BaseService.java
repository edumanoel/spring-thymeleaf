package br.com.mbds.springthymeleaf.services;

import java.util.List;
import java.util.Optional;

public interface BaseService<T> {

	public T save(T entity);

	public void delete(Long id);

	public Optional<T> findById(Long id);

	public List<T> findAll();

}
