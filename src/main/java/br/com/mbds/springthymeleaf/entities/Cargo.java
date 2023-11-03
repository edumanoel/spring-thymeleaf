package br.com.mbds.springthymeleaf.entities;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

@SuppressWarnings("serial")
@Getter
@Setter
@Entity
@Table(name = "CARGOS")
public class Cargo extends AbstractEntity<Long> {

	@NotBlank(message = "Informe um nome.")
	@Size(min = 3, max = 60, message = "O nome deve ter entre {min} e {max} caracteres.")
	@Column(nullable = false, unique = true, length = 60)
	private String nome;

	@NotNull(message = "Selecione um departamento.")
	@ManyToOne
	@JoinColumn(name = "departamento_id")
	private Departamento departamento;

	@Setter(AccessLevel.NONE)
	@OneToMany(mappedBy = "cargo")
	private List<Funcionario> funcionarios;

}
