package br.com.asas.lojinha.controller;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.asas.lojinha.entity.Fabricante;
import br.com.asas.lojinha.exception.LojinhaNotFoundException;
import br.com.asas.lojinha.service.FabricanteService;

@Controller
@Transactional
@RequestMapping("/fabricante")
public class FabricanteController {
	
	@Autowired
	private FabricanteService fabricanteService;
	
	private static final Logger log = LoggerFactory.getLogger(FabricanteController.class);
  
    @RequestMapping(value = "/lista", method = RequestMethod.GET)
    public ModelAndView listar() {
    	log.debug("Listando fabricantes");
        ModelAndView modelAndView = new ModelAndView("fabricante/fabricantes");
        modelAndView.addObject("fabricantes", fabricanteService.buscarTodos());
        return modelAndView;
    }
    
    @RequestMapping(value = "/adicionar", method = RequestMethod.GET)
    public String adicionar(Model model) {
    	log.debug("Acessando página para adicionar fabricante.");
    	model.addAttribute("fabricante", new Fabricante());
    	return "fabricante/adicionar";    	
    }
    
    @RequestMapping(value = "/adicionar", method = RequestMethod.POST)
    public String salvar(@Valid  Fabricante fabricante, BindingResult result, RedirectAttributes redirect) {
    	log.debug("Adicionando um novo fabricante {}", fabricante);
    	if(result.hasFieldErrors()) {
    		log.debug("Tentativa de salvar um novo fabricante, mas falta dado obrigatório.");
    		return "fabricante/adicionar";
    	}
    	
    	this.fabricanteService.salvar(fabricante);
    	log.debug("Fabricante adicionado com sucesso", fabricante);
    	redirect.addFlashAttribute("sucesso", "Fabricante " +fabricante.getNome()+ " cadastrado com sucesso.");
    	return "redirect:/fabricante/lista";
    }
    
    @RequestMapping(value = "/atualizar/{codigo}", method = RequestMethod.GET)
    public ModelAndView atualizar(@PathVariable("codigo") Long codigo) throws LojinhaNotFoundException {
    	log.debug("Acessando página para atualizar fabricante.");
    	ModelAndView modelAndView = new ModelAndView("fabricante/atualizar");
    	modelAndView.addObject("fabricante", fabricanteService.encontrarUm(codigo));
    	return modelAndView;
    }
    
    @RequestMapping(value = "/atualizar/{codigo}", method = RequestMethod.POST)
    public String alterado(@Valid Fabricante fabricante,  BindingResult result,
    		RedirectAttributes redirect) {
    	log.debug("Atualizando um fabricante {}", fabricante);
    	if(result.hasFieldErrors()) {
    		log.debug("Tentativa de atualizar um fabricante, mas falta dado obrigatório.");
    		return "fabricante/atualizar";
    	}    	
    	fabricanteService.atualizar(fabricante);
    	log.debug("Fabricante atualizado com sucesso", fabricante);
    	redirect.addFlashAttribute("sucesso", "Fabricante " +fabricante.getNome()+ " atualizado com sucesso.");
    	return "redirect:/fabricante/lista";
    }
    
    @RequestMapping(value = "/excluir/{codigo}", method = RequestMethod.GET)
    public ModelAndView excluir(@PathVariable("codigo") Long codigo) throws LojinhaNotFoundException {
    	log.debug("Acessando página para excluir fabricante.");
    	ModelAndView modelAndView = new ModelAndView("/fabricante/excluir");
    	modelAndView.addObject("fabricante", fabricanteService.encontrarUm(codigo));
    	return modelAndView;
    }
    
    @RequestMapping(value = "/excluir/{codigo}", method = RequestMethod.DELETE)
    public ModelAndView excluido(Fabricante fabricante, RedirectAttributes redirect) {
    	log.debug("Excluindo um fabricante {}", fabricante);
    	ModelAndView modelAndView = new ModelAndView("redirect:/fabricante/lista");
    	fabricanteService.deletar(fabricante);
    	log.debug("Fabricante excluido com sucesso", fabricante);
    	redirect.addFlashAttribute("sucesso", "Fabricante " +fabricante.getNome()+ " excluido com sucesso.");
    	return modelAndView;
    }
}
