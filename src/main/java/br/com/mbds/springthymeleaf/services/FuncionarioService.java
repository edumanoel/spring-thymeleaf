package br.com.mbds.springthymeleaf.services;

import java.util.List;

import br.com.mbds.springthymeleaf.entities.Funcionario;

public interface FuncionarioService extends BaseService<Funcionario> {

	List<Funcionario> findByNome(String nome);

}
