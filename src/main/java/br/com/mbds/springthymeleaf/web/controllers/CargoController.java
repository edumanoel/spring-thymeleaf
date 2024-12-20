package br.com.mbds.springthymeleaf.web.controllers;

import java.util.List;
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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.mbds.springthymeleaf.entities.Cargo;
import br.com.mbds.springthymeleaf.entities.Departamento;
import br.com.mbds.springthymeleaf.services.CargoService;
import br.com.mbds.springthymeleaf.services.DepartamentoService;

@Controller
@RequestMapping("cargos")
public class CargoController {

	@Autowired
	private CargoService cargoService;

	@Autowired
	private DepartamentoService departamentoService;

	@GetMapping("cadastrar")
	public String cadastrar(Cargo cargo) {
		return "cargo/cadastro";
	}

	@GetMapping("listar")
	public String listar(ModelMap model, @RequestParam Optional<Integer> page) {
		int itensPorPagina = 5;
		Pageable pageable = PageRequest.of(page.orElse(1) - 1, itensPorPagina, Sort.by("nome").ascending());
		Page<Cargo> dados = cargoService.findAll(pageable);
		model.addAttribute("listaCargos", dados.getContent());
		model.addAttribute("totalPaginas", dados.getTotalPages());
		model.addAttribute("pagina", dados.getNumber() + 1);
		return "cargo/lista";
	}

	@PostMapping("salvar")
	public String salvar(@Validated Cargo cargo, BindingResult result, RedirectAttributes attr) {
		if (result.hasErrors()) {
			return "cargo/cadastro";
		}
		cargoService.save(cargo);
		attr.addFlashAttribute("success", "Cargo salvo com sucesso.");
		return "redirect:/cargos/cadastrar";
	}

	@GetMapping("editar/{id}")
	public String editar(@PathVariable Long id, ModelMap model) {
		model.addAttribute("cargo", cargoService.findById(id));
		return "cargo/cadastro";
	}

	@GetMapping("excluir/{id}")
	public String excluir(@PathVariable Long id, RedirectAttributes attr) {
		cargoService.delete(id);
		attr.addFlashAttribute("success", "Cargo excluído com sucesso.");
		return "redirect:/cargos/listar";
	}

	@ModelAttribute("listaDepartamentos")
	public List<Departamento> getListaDepartamentos() {
		return departamentoService.findAll();
	}

}
