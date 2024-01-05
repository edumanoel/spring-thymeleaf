package br.com.mbds.springthymeleaf.repositories;

import java.time.LocalDate;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.mbds.springthymeleaf.entities.Cargo;
import br.com.mbds.springthymeleaf.entities.Funcionario;

public interface FuncionarioRepository extends JpaRepository<Funcionario, Long> {

	Page<Funcionario> findByNomeContaining(String nome, Pageable pageable);

	Page<Funcionario> findByCargo(Cargo cargo, Pageable pageable);

	Page<Funcionario> findByDataEntrada(LocalDate data, Pageable pageable);

	Page<Funcionario> findByDataSaida(LocalDate data, Pageable pageable);

	@Query("SELECT f FROM Funcionario f WHERE f.dataEntrada >= :entrada AND f.dataSaida <= :saida ORDER BY f.dataEntrada")
	Page<Funcionario> findbyBetweenDataEntradaAndDataSaida(@Param("entrada") LocalDate dataEntrada,
			@Param("saida") LocalDate dataSaida, Pageable pageable);

}
