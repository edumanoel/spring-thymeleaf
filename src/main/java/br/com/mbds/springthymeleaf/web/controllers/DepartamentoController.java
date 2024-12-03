package br.com.mbds.springthymeleaf.web.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
		return "departamento/cadastro";
	}

	@GetMapping("listar")
	public String listar(ModelMap model, @RequestParam Optional<Integer> page) {
		int itensPorPagina = 5;
		Pageable pageable = PageRequest.of(page.orElse(1) - 1, itensPorPagina, Sort.by("nome").ascending());
		Page<Departamento> dados = service.findAll(pageable);
		model.addAttribute("listaDepartamentos", dados.getContent());
		model.addAttribute("totalPaginas", dados.getTotalPages());
		model.addAttribute("pagina", dados.getNumber() + 1);
		return "departamento/lista";
	}

	@PostMapping("salvar")
	public String salvar(@Validated Departamento departamento, BindingResult result, RedirectAttributes attr) {
		if (result.hasErrors()) {
			return "departamento/cadastro";
		}
		service.save(departamento);
		attr.addFlashAttribute("success", "Departamento salvo com sucesso.");
		return "redirect:/departamentos/cadastrar";
	}

	@GetMapping("editar/{id}")
	public String editar(@PathVariable Long id, ModelMap model) {
		model.addAttribute("departamento", service.findById(id));
		return "departamento/cadastro";
	}

	@GetMapping("excluir/{id}")
	public String excluir(@PathVariable Long id, RedirectAttributes attr) {
		service.delete(id);
		attr.addFlashAttribute("success", "Departamento excluído com sucesso.");
		return "redirect:/departamentos/listar";
	}

}
