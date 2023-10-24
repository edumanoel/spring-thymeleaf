package br.com.mbds.springthymeleaf.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.mbds.springthymeleaf.entities.Cargo;
import br.com.mbds.springthymeleaf.repositories.CargoRepository;

@Service
public class CargoServiceImpl implements CargoService {

	@Autowired
	private CargoRepository repository;

	@Transactional
	@Override
	public Cargo save(Cargo entity) {
		return repository.save(entity);
	}

	@Transactional
	@Override
	public void delete(Long id) {
		repository.deleteById(id);
	}

	@Override
	public Optional<Cargo> findById(Long id) {
		return repository.findById(id);
	}

	@Override
	public List<Cargo> findAll() {
		return repository.findAll();
	}

}
