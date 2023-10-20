package br.com.mbds.springthymeleaf.exceptions;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice
public class WebExceptionHandler {

	@ExceptionHandler(DataBaseException.class)
	public String dataBaseError(DataBaseException e, RedirectAttributes attr, HttpServletRequest request) {
		attr.addFlashAttribute("error", "Erro no banco de dados!");
		attr.addFlashAttribute("error_detail", e.getMessage());
		var uri = request.getRequestURI().contains("salvar") ? request.getRequestURI().replace("salvar", "cadastrar")
				: request.getRequestURI().replace("excluir", "listar");
		return "redirect:" + uri;
	}
}
