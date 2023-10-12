package br.com.mbds.springthymeleaf.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.mbds.springthymeleaf.entities.Funcionario;

public interface FuncionarioRepository extends JpaRepository<Funcionario, Long> {

}
