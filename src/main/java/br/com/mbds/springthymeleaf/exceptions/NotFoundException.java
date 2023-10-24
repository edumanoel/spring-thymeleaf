package br.com.mbds.springthymeleaf.exceptions;

public class NotFoundException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public NotFoundException(Object obj) {
		super("Not Found Error! Search params: " + obj.toString());
	}
}
