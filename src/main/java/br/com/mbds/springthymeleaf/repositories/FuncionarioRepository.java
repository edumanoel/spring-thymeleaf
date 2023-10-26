package br.com.mbds.springthymeleaf.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.mbds.springthymeleaf.entities.Funcionario;

public interface FuncionarioRepository extends JpaRepository<Funcionario, Long> {

	List<Funcionario> findByNomeContaining(String nome);

}
