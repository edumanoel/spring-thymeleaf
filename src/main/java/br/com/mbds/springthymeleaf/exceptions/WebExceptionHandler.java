package br.com.mbds.springthymeleaf.exceptions;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@ControllerAdvice
public class WebExceptionHandler {

	@ExceptionHandler(DataBaseException.class)
	public String DataBaseError(DataBaseException e, RedirectAttributes attr) {
		attr.addFlashAttribute("error", "Erro no banco de dados!");
		attr.addFlashAttribute("error_detail", e.getMessage());

		// TODO Obter URI de retorno dinamicamente
		return "redirect:/departamentos/cadastrar";
	}
}
