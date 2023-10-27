package br.com.mbds.springthymeleaf.repositories;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.mbds.springthymeleaf.entities.Cargo;
import br.com.mbds.springthymeleaf.entities.Funcionario;

public interface FuncionarioRepository extends JpaRepository<Funcionario, Long> {

	List<Funcionario> findByNomeContaining(String nome);

	List<Funcionario> findByCargo(Cargo cargo);

	List<Funcionario> findByDataEntrada(LocalDate data);

	List<Funcionario> findByDataSaida(LocalDate data);

	@Query("SELECT f FROM Funcionario f WHERE f.dataEntrada >= :entrada AND f.dataSaida <= :saida ORDER BY f.dataEntrada")
	List<Funcionario> findbyBetweenDataEntradaAndDataSaida(@Param("entrada") LocalDate dataEntrada,
			@Param("saida") LocalDate dataSaida);

}
