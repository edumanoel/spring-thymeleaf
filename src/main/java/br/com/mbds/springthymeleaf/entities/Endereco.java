package br.com.mbds.springthymeleaf.entities;

import br.com.mbds.springthymeleaf.entities.enums.UF;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
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
@Table(name = "ENDERECOS")
public class Endereco extends AbstractEntity<Long> {

	@NotBlank
	@Size(min = 3, max = 255)
	@Column(nullable = false)
	private String logradouro;

	@NotBlank
	@Size(min = 3, max = 255)
	@Column(nullable = false)
	private String bairro;

	@NotBlank
	@Size(min = 3, max = 255)
	@Column(nullable = false)
	private String cidade;

	@NotNull(message = "{uf.NotNull.message}")
	@Column(nullable = false, length = 2)
	@Enumerated(EnumType.STRING)
	private UF uf;

	@NotBlank
	@Size(min = 9, max = 9, message = "{endereco.cep.Size.message}")
	@Column(nullable = false, length = 9)
	private String cep;

	@NotNull(message = "{endereco.numero.NotNull.message}")
	@Digits(integer = 5, fraction = 0)
	@Column(nullable = false, length = 5)
	private Integer numero;

	@Size(max = 255)
	private String complemento;

}
