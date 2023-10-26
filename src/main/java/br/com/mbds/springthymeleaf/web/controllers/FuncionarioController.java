package br.com.mbds.springthymeleaf.web.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.mbds.springthymeleaf.entities.Cargo;
import br.com.mbds.springthymeleaf.entities.Funcionario;
import br.com.mbds.springthymeleaf.entities.enums.UF;
import br.com.mbds.springthymeleaf.services.CargoService;
import br.com.mbds.springthymeleaf.services.FuncionarioService;

@Controller
@RequestMapping("funcionarios")
public class FuncionarioController {

	@Autowired
	private FuncionarioService funcionarioService;

	@Autowired
	private CargoService cargoService;

	@GetMapping("cadastrar")
	public String cadastrar(Funcionario funcionario) {
		return "/funcionario/cadastro";
	}

	@GetMapping("listar")
	public String listar(ModelMap model) {
		model.addAttribute("listaFuncionarios", funcionarioService.findAll());
		return "/funcionario/lista";
	}

	@PostMapping("salvar")
	public String salvar(Funcionario funcionario, RedirectAttributes attr) {
		funcionarioService.save(funcionario);
		attr.addFlashAttribute("success", "Funcionario salvo com sucesso.");
		return "redirect:/funcionarios/cadastrar";
	}

	@GetMapping("editar/{id}")
	public String editar(@PathVariable Long id, ModelMap model) {
		model.addAttribute("funcionario", funcionarioService.findById(id));
		return "/funcionario/cadastro";
	}

	@GetMapping("excluir/{id}")
	public String excluir(@PathVariable Long id, ModelMap model) {
		funcionarioService.delete(id);
		model.addAttribute("success", "Funcionario excluído com sucesso.");
		return listar(model);
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
