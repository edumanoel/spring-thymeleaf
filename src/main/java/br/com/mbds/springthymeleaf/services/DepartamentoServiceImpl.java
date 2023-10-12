package br.com.mbds.springthymeleaf.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.mbds.springthymeleaf.entities.Departamento;
import br.com.mbds.springthymeleaf.repositories.DepartamentoRepository;
import jakarta.transaction.Transactional;

@Service
public class DepartamentoServiceImpl implements DepartamentoService {

	@Autowired
	private DepartamentoRepository repository;

	@Transactional
	@Override
	public Departamento insert(Departamento entity) {
		return repository.save(entity);
	}

	@Transactional
	@Override
	public Departamento update(Departamento entity) {
		return repository.save(entity);
	}

	@Transactional
	@Override
	public void delete(Long id) {
		repository.deleteById(id);
	}

	@Override
	public Optional<Departamento> findById(Long id) {
		return repository.findById(id);
	}

	@Override
	public List<Departamento> findAll() {
		return repository.findAll();
	}

}
