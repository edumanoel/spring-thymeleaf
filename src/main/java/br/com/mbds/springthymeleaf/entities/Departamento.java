package br.com.mbds.springthymeleaf.entities;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
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
@Table(name = "DEPARTAMENTOS")
public class Departamento extends AbstractEntity<Long> {

	@NotBlank(message = "{departamento.nome.NotBlank.message}")
	@Size(min = 3, max = 60, message = "{departamento.nome.Size.message}")
	@Column(nullable = false, unique = true, length = 60)
	private String nome;

	@Setter(AccessLevel.NONE)
	@OneToMany(mappedBy = "departamento")
	private List<Cargo> cargos;

}
