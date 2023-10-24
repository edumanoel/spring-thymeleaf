package br.com.mbds.springthymeleaf.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.mbds.springthymeleaf.entities.Funcionario;
import br.com.mbds.springthymeleaf.repositories.FuncionarioRepository;

@Service
public class FuncionarioServiceImpl implements FuncionarioService {

	@Autowired
	private FuncionarioRepository repository;

	@Transactional
	@Override
	public Funcionario save(Funcionario entity) {
		return repository.save(entity);
	}

	@Transactional
	@Override
	public void delete(Long id) {
		repository.deleteById(id);
	}

	@Override
	public Optional<Funcionario> findById(Long id) {
		return repository.findById(id);
	}

	@Override
	public List<Funcionario> findAll() {
		return repository.findAll();
	}

}
