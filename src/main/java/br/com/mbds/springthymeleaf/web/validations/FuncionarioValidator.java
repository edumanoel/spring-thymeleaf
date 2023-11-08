package br.com.mbds.springthymeleaf.web.validations;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import br.com.mbds.springthymeleaf.entities.Funcionario;

public class FuncionarioValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return Funcionario.class.equals(clazz);
	}

	@Override
	public void validate(Object object, Errors errors) {
		Funcionario f = (Funcionario) object;
		if (f.getDataSaida() != null) {
			if (f.getDataSaida().isBefore(f.getDataEntrada())) {
				errors.rejectValue("dataSaida", "funcionario.dataSaida.IsBeforeDataEntrada.message");
			}
		}
	}

}
