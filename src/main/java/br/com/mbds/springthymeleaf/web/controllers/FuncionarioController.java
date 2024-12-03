package br.com.mbds.springthymeleaf.web.controllers;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.mbds.springthymeleaf.entities.Cargo;
import br.com.mbds.springthymeleaf.entities.Funcionario;
import br.com.mbds.springthymeleaf.entities.enums.UF;
import br.com.mbds.springthymeleaf.services.CargoService;
import br.com.mbds.springthymeleaf.services.FuncionarioService;
import br.com.mbds.springthymeleaf.web.validations.FuncionarioValidator;

@Controller
@RequestMapping("funcionarios")
public class FuncionarioController {

	private static final int ITENS_POR_PAGINA = 5;

	@Autowired
	private FuncionarioService funcionarioService;

	@Autowired
	private CargoService cargoService;

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.addValidators(new FuncionarioValidator());
	}

	@GetMapping("cadastrar")
	public String cadastrar(Funcionario funcionario) {
		return "funcionario/cadastro";
	}

	@GetMapping("listar")
	public String listar(ModelMap model, @RequestParam Optional<Integer> page) {
		Pageable pageable = PageRequest.of(page.orElse(1) - 1, ITENS_POR_PAGINA, Sort.by("nome").ascending());
		Page<Funcionario> dados = funcionarioService.findAll(pageable);
		setAttributeModel(model, dados);
		return "funcionario/lista";
	}

	@PostMapping("salvar")
	public String salvar(@Validated Funcionario funcionario, BindingResult result, RedirectAttributes attr) {
		if (result.hasErrors()) {
			return "funcionario/cadastro";
		}
		funcionarioService.save(funcionario);
		attr.addFlashAttribute("success", "Funcionario salvo com sucesso.");
		return "redirect:/funcionarios/cadastrar";
	}

	@GetMapping("editar/{id}")
	public String editar(@PathVariable Long id, ModelMap model) {
		model.addAttribute("funcionario", funcionarioService.findById(id));
		return "funcionario/cadastro";
	}

	@GetMapping("excluir/{id}")
	public String excluir(@PathVariable Long id, RedirectAttributes attr) {
		funcionarioService.delete(id);
		attr.addFlashAttribute("success", "Funcionario excluído com sucesso.");
		return "redirect:/funcionarios/listar";
	}

	@GetMapping("buscar/nome")
	public String getFuncionarioPorNome(ModelMap model, @RequestParam Optional<Integer> page,
			@RequestParam String nome) {
		Pageable pageable = PageRequest.of(page.orElse(1) - 1, ITENS_POR_PAGINA, Sort.by("nome").ascending());
		Page<Funcionario> dados = funcionarioService.findByNome(nome, pageable);
		setAttributeModel(model, dados);
		return "funcionario/lista";
	}

	@GetMapping("buscar/cargo")
	public String getFuncionarioPorCargo(ModelMap model, @RequestParam Optional<Integer> page,
			@RequestParam Long id) {
		var cargo = cargoService.findById(id);
		Pageable pageable = PageRequest.of(page.orElse(1) - 1, ITENS_POR_PAGINA, Sort.by("nome").ascending());
		Page<Funcionario> dados = funcionarioService.findByCargo(cargo, pageable);
		setAttributeModel(model, dados);
		return "funcionario/lista";
	}

	@GetMapping("buscar/data")
	public String getFuncionarioPorData(ModelMap model, @RequestParam Optional<Integer> page,
			@RequestParam(required = false) @DateTimeFormat(iso = ISO.DATE) LocalDate entrada,
			@RequestParam(required = false) @DateTimeFormat(iso = ISO.DATE) LocalDate saida) {
		Pageable pageable = PageRequest.of(page.orElse(1) - 1, ITENS_POR_PAGINA, Sort.by("nome").ascending());
		Page<Funcionario> dados = funcionarioService.findByData(entrada, saida, pageable);
		setAttributeModel(model, dados);
		return "funcionario/lista";
	}

	@ModelAttribute("listaCargos")
	public List<Cargo> getListaCargos() {
		return cargoService.findAll();
	}

	@ModelAttribute("listaUFs")
	public UF[] getUFs() {
		return UF.values();
	}

	private void setAttributeModel(ModelMap model, Page<Funcionario> page) {
		model.addAttribute("listaFuncionarios", page.getContent());
		model.addAttribute("totalPaginas", page.getTotalPages());
		model.addAttribute("pagina", page.getNumber() + 1);
	}

}
