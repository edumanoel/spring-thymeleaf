package br.com.mbds.springthymeleaf.config;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import br.com.mbds.springthymeleaf.entities.Cargo;
import br.com.mbds.springthymeleaf.entities.Departamento;
import br.com.mbds.springthymeleaf.entities.Endereco;
import br.com.mbds.springthymeleaf.entities.Funcionario;
import br.com.mbds.springthymeleaf.entities.enums.UF;
import br.com.mbds.springthymeleaf.repositories.CargoRepository;
import br.com.mbds.springthymeleaf.repositories.DepartamentoRepository;
import br.com.mbds.springthymeleaf.repositories.FuncionarioRepository;

@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner {

	@Autowired
	DepartamentoRepository departamentoRepository;

	@Autowired
	CargoRepository cargoRepository;

	@Autowired
	FuncionarioRepository funcionarioRepository;

	@Override
	public void run(String... args) throws Exception {

		List<Departamento> departamentos = departamentoRepository
				.saveAll(Arrays.asList(new Departamento("Administração", null), new Departamento("Financeiro", null),
						new Departamento("Vendas", null), new Departamento("Logística", null)));

		List<Cargo> cargos = cargoRepository
				.saveAll(Arrays.asList(new Cargo("Administrador", departamentos.get(0), null),
						new Cargo("Contador", departamentos.get(1), null),
						new Cargo("Gerente de Vendas", departamentos.get(2), null),
						new Cargo("Vendedor", departamentos.get(2), null),
						new Cargo("Gerente de Logística", departamentos.get(3), null),
						new Cargo("Supervisor de Estoque", departamentos.get(3), null)));

		Endereco e1 = new Endereco("Avenida 7 de Setembro", "Campo Grande", "Salvador", UF.BA, "41000-000", 123, null);
		Endereco e2 = new Endereco("Avenida 7 de Setembro", "Campo Grande", "Salvador", UF.BA, "41000-000", 456, null);
		Endereco e3 = new Endereco("Rua Politeama", "Politeama", "Salvador", UF.BA, "41000-000", 789, null);
		Endereco e4 = new Endereco("Avenida Carlos Gomes", "Centro", "Salvador", UF.BA, "41000-000", 123, null);
		Endereco e5 = new Endereco("Avenida Carlos Gomes", "Centro", "Salvador", UF.BA, "41000-000", 456, null);
		Endereco e6 = new Endereco("Avenida Mário Leal", "Brotas", "Salvador", UF.BA, "41000-000", 123, null);
		Endereco e7 = new Endereco("Avenida Mário Leal", "Brotas", "Salvador", UF.BA, "41000-000", 123, null);

		funcionarioRepository.saveAll(Arrays.asList(
				new Funcionario("Eduardo Belém", BigDecimal.valueOf(4800.00), LocalDate.now(), null, e1, cargos.get(0)),
				new Funcionario("Maria Joanna", BigDecimal.valueOf(3900.00), LocalDate.now(), null, e2, cargos.get(1)),
				new Funcionario("Mário Santos", BigDecimal.valueOf(4200.00), LocalDate.now(), null, e3, cargos.get(2)),
				new Funcionario("Marcos Silva", BigDecimal.valueOf(2600.00), LocalDate.now(), null, e4, cargos.get(3)),
				new Funcionario("Joice Souza", BigDecimal.valueOf(2700.00), LocalDate.now(), null, e5, cargos.get(3)),
				new Funcionario("Carla Pereira", BigDecimal.valueOf(4100.00), LocalDate.now(), null, e6, cargos.get(3)),
				new Funcionario("Davi Matos", BigDecimal.valueOf(3100.00), LocalDate.now(), null, e7, cargos.get(5))));

	}

}
