package br.com.mbds.springthymeleaf.services;

import java.time.LocalDate;
import java.util.List;

import br.com.mbds.springthymeleaf.entities.Cargo;
import br.com.mbds.springthymeleaf.entities.Funcionario;

public interface FuncionarioService extends BaseService<Funcionario> {

	List<Funcionario> findByNome(String nome);

	List<Funcionario> findByCargo(Cargo cargo);

	List<Funcionario> findByData(LocalDate dataEntrada, LocalDate dataSaida);

}
