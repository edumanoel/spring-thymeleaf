package br.com.mbds.springthymeleaf.services;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.mbds.springthymeleaf.entities.Cargo;
import br.com.mbds.springthymeleaf.entities.Funcionario;
import br.com.mbds.springthymeleaf.exceptions.DataBaseException;
import br.com.mbds.springthymeleaf.exceptions.NotFoundException;
import br.com.mbds.springthymeleaf.repositories.FuncionarioRepository;

@Service
public class FuncionarioServiceImpl implements FuncionarioService {

	@Autowired
	private FuncionarioRepository repository;

	@Transactional(propagation = Propagation.SUPPORTS)
	@Override
	public Funcionario save(Funcionario entity) {
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
	public Funcionario findById(Long id) {
		return repository.findById(id).orElseThrow(() -> new NotFoundException(id));
	}

	@Override
	public List<Funcionario> findAll() {
		return repository.findAll();
	}

	@Override
	public Page<Funcionario> findAll(Pageable pageable) {
		return repository.findAll(pageable);
	}

	@Override
	public List<Funcionario> findByNome(String nome) {
		return repository.findByNomeContaining(nome);
	}

	@Override
	public List<Funcionario> findByCargo(Cargo cargo) {
		return repository.findByCargo(cargo);
	}

	@Override
	public List<Funcionario> findByData(LocalDate dataEntrada, LocalDate dataSaida) {
		if (dataEntrada != null && dataSaida != null) {
			return repository.findbyBetweenDataEntradaAndDataSaida(dataEntrada, dataSaida);
		} else if (dataEntrada != null) {
			return repository.findByDataEntrada(dataEntrada);
		} else if (dataSaida != null) {
			return repository.findByDataSaida(dataSaida);
		} else {
			return new ArrayList<>();
		}
	}

}
