package br.com.mbds.springthymeleaf.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.mbds.springthymeleaf.entities.Departamento;
import br.com.mbds.springthymeleaf.services.DepartamentoService;

@Controller
@RequestMapping("departamentos")
public class DepartamentoController {

	@Autowired
	private DepartamentoService service;

	@GetMapping("cadastrar")
	public String cadastrar(Departamento departamento) {
		return "/departamento/cadastro";
	}

	@GetMapping("listar")
	public String listar(ModelMap model) {
		model.addAttribute("listaDepartamentos", service.findAll());
		return "/departamento/lista";
	}

	@PostMapping("salvar")
	public String salvar(Departamento departamento, RedirectAttributes attr) {
		service.save(departamento);
		attr.addFlashAttribute("success", "Departamento salvo com sucesso.");
		return "redirect:/departamentos/cadastrar";
	}

	@GetMapping("editar/{id}")
	public String editar(@PathVariable Long id, ModelMap model) {
		model.addAttribute("departamento", service.findById(id));
		return "/departamento/cadastro";
	}

	@GetMapping("excluir/{id}")
	public String excluir(@PathVariable Long id, ModelMap model) {
		service.delete(id);
		model.addAttribute("success", "Departamento excluído com sucesso.");
		return listar(model);
	}

}
