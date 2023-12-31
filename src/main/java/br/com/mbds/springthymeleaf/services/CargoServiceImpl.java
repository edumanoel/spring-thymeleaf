package br.com.mbds.springthymeleaf.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.mbds.springthymeleaf.entities.Cargo;
import br.com.mbds.springthymeleaf.exceptions.DataBaseException;
import br.com.mbds.springthymeleaf.exceptions.NotFoundException;
import br.com.mbds.springthymeleaf.repositories.CargoRepository;

@Service
public class CargoServiceImpl implements CargoService {

	@Autowired
	private CargoRepository repository;

	@Transactional(propagation = Propagation.SUPPORTS)
	@Override
	public Cargo save(Cargo entity) {
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
	public Cargo findById(Long id) {
		return repository.findById(id).orElseThrow(() -> new NotFoundException(id));
	}

	@Override
	public List<Cargo> findAll() {
		return repository.findAll();
	}

	@Override
	public Page<Cargo> findAll(Pageable pageable) {
		return repository.findAll(pageable);
	}

}
