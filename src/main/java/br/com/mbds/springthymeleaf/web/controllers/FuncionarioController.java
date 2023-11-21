package br.com.mbds.springthymeleaf.web.controllers;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
	public String listar(ModelMap model) {
		model.addAttribute("listaFuncionarios", funcionarioService.findAll());
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
	public String excluir(@PathVariable Long id, ModelMap model) {
		funcionarioService.delete(id);
		model.addAttribute("success", "Funcionario excluído com sucesso.");
		return listar(model);
	}

	@GetMapping("buscar/nome")
	public String getFuncionarioPorNome(@RequestParam("nome") String nome, ModelMap model) {
		model.addAttribute("listaFuncionarios", funcionarioService.findByNome(nome));
		return "funcionario/lista";
	}

	@GetMapping("buscar/cargo")
	public String getFuncionarioPorCargo(@RequestParam("id") Long id, ModelMap model) {
		var cargo = cargoService.findById(id);
		model.addAttribute("listaFuncionarios", funcionarioService.findByCargo(cargo));
		return "funcionario/lista";
	}

	@GetMapping("buscar/data")
	public String getFuncionarioPorData(ModelMap model,
			@RequestParam(required = false) @DateTimeFormat(iso = ISO.DATE) LocalDate entrada,
			@RequestParam(required = false) @DateTimeFormat(iso = ISO.DATE) LocalDate saida) {
		model.addAttribute("listaFuncionarios", funcionarioService.findByData(entrada, saida));
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
}
