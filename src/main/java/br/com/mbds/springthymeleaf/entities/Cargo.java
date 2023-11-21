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
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@SuppressWarnings("serial")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "CARGOS")
public class Cargo extends AbstractEntity<Long> {

	@NotBlank(message = "{cargo.nome.NotBlank.message}")
	@Size(min = 3, max = 60, message = "{cargo.nome.Size.message}")
	@Column(nullable = false, unique = true, length = 60)
	private String nome;

	@NotNull(message = "{departamento.NotNull.message}")
	@ManyToOne
	@JoinColumn(name = "departamento_id")
	private Departamento departamento;

	@Setter(AccessLevel.NONE)
	@OneToMany(mappedBy = "cargo")
	private List<Funcionario> funcionarios;

}
