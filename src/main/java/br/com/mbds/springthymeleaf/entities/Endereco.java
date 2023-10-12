package br.com.mbds.springthymeleaf.entities;

import br.com.mbds.springthymeleaf.entities.enums.UF;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@SuppressWarnings("serial")
@Getter
@Setter
@Entity
@Table(name = "ENDERECOS")
public class Endereco extends AbstractEntity<Long> {

	@Column(nullable = false)
	private String logradouro;

	@Column(nullable = false)
	private String bairro;

	@Column(nullable = false)
	private String cidade;

	@Column(nullable = false, length = 2)
	@Enumerated(EnumType.STRING)
	private UF uf;

	@Column(nullable = false, length = 9)
	private String cep;

	private Integer numero;

	private String complemento;

}
