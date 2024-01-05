package br.com.mbds.springthymeleaf.services;

import java.time.LocalDate;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.com.mbds.springthymeleaf.entities.Cargo;
import br.com.mbds.springthymeleaf.entities.Funcionario;

public interface FuncionarioService extends BaseService<Funcionario> {

	Page<Funcionario> findByNome(String nome, Pageable pageable);

	Page<Funcionario> findByCargo(Cargo cargo, Pageable pageable);

	Page<Funcionario> findByData(LocalDate dataEntrada, LocalDate dataSaida, Pageable pageable);

}
