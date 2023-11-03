package br.com.mbds.springthymeleaf.exceptions;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import jakarta.validation.ConstraintViolation;
import lombok.Getter;

@Getter
public class ValidatedException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	private final List<String> messageErrors = new ArrayList<>();

	public ValidatedException(String msg, Set<ConstraintViolation<?>> errors) {
		super(msg);
		for (ConstraintViolation<?> err : errors) {
			messageErrors.add(err.getMessage());
		}
	}
}
