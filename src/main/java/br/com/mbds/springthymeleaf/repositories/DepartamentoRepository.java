package br.com.mbds.springthymeleaf.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.mbds.springthymeleaf.entities.Departamento;

public interface DepartamentoRepository extends JpaRepository<Departamento, Long> {

}
