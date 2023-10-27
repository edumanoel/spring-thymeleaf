package br.com.mbds.springthymeleaf.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.mbds.springthymeleaf.entities.Departamento;
import br.com.mbds.springthymeleaf.exceptions.DataBaseException;
import br.com.mbds.springthymeleaf.exceptions.NotFoundException;
import br.com.mbds.springthymeleaf.repositories.DepartamentoRepository;

@Service
public class DepartamentoServiceImpl implements DepartamentoService {

	@Autowired
	private DepartamentoRepository repository;

	@Transactional(propagation = Propagation.SUPPORTS)
	@Override
	public Departamento save(Departamento entity) {
		try {
			return repository.save(entity);
		} catch (DataIntegrityViolationException e) {
			throw new DataBaseException(e.getMessage());
		}
	}

	@Transactional(propagation = Propagation.SUPPORTS)
	@Override
	public void delete(Long id) {
		try {
			if (repository.existsById(id)) {
				repository.deleteById(id);
			} else {
				throw new NotFoundException(id);
			}
		} catch (DataIntegrityViolationException e) {
			throw new DataBaseException(e.getMessage());
		}
	}

	@Override
	public Departamento findById(Long id) {
		return repository.findById(id).orElseThrow(() -> new NotFoundException(id));
	}

	@Override
	public List<Departamento> findAll() {
		return repository.findAll();
	}

}
