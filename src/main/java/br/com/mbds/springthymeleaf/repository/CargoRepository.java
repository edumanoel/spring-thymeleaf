package br.com.mbds.springthymeleaf.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.mbds.springthymeleaf.entities.Cargo;

public interface CargoRepository extends JpaRepository<Cargo, Long> {

}
