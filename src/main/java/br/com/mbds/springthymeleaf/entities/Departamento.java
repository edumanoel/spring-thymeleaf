package br.com.mbds.springthymeleaf.entities;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

@SuppressWarnings("serial")
@Getter
@Setter
@Entity
@Table(name = "DEPARTAMENTOS")
public class Departamento extends AbstractEntity<Long> {

	@Column(nullable = false, unique = true, length = 60)
	private String nome;

	@Setter(AccessLevel.NONE)
	@OneToMany(mappedBy = "departamento")
	private List<Cargo> cargos;

}
