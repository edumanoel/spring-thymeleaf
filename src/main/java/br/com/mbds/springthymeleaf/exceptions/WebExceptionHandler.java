package br.com.mbds.springthymeleaf.exceptions;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice
public class WebExceptionHandler {

	@ExceptionHandler(DataBaseException.class)
	public String dataBaseError(DataBaseException e, RedirectAttributes attr, HttpServletRequest request) {
		attr.addFlashAttribute("error", "Erro no banco de dados.");
		attr.addFlashAttribute("error_detail", e.getMessage());
		return uriRedirect(request);
	}

	@ExceptionHandler(NotFoundException.class)
	public String notFoundError(NotFoundException e, RedirectAttributes attr, HttpServletRequest request) {
		attr.addFlashAttribute("error", "Nenhum registro encontrado.");
		attr.addFlashAttribute("error_detail", e.getMessage());
		return uriRedirect(request);
	}

	@ExceptionHandler(ValidatedException.class)
	public String validatedError(ValidatedException e, RedirectAttributes attr, HttpServletRequest request) {
		attr.addFlashAttribute("errorslist", e.getMessageErrors());
		return uriRedirect(request);
	}

	private String uriRedirect(HttpServletRequest request) {
		var pathAttrs = request.getRequestURI().split("/");
		var resource = pathAttrs[1];
		var action = pathAttrs[2].contains("salvar") ? pathAttrs[2].replace("salvar", "cadastrar") : "listar";
		return "redirect:/" + resource + "/" + action;
	}
}
