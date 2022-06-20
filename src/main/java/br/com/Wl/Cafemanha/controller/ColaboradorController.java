package br.com.Wl.Cafemanha.controller;


import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.Wl.Cafemanha.dto.RequisicaoFormColaborador;
import br.com.Wl.Cafemanha.model.Colaborador;
import br.com.Wl.Cafemanha.repository.ColaboradorRepository;

@Controller
@RequestMapping(value = "/colaboradores")
public class ColaboradorController {

	@Autowired
	private ColaboradorRepository colaboradorRepository;
	
	@GetMapping("")
	public ModelAndView index() {
		
		List<Colaborador> colaboradores = this.colaboradorRepository.findAll();
		
		ModelAndView mv = new ModelAndView("colaboradores/index");
		mv.addObject("colaboradores", colaboradores);		
				return mv;
	}
	
	@GetMapping("/new")
	public ModelAndView nnew(RequisicaoFormColaborador requisicaoFormColaborador) {
		ModelAndView mv = new ModelAndView("colaboradores/new");
		
		return mv;
	}
	
	
	
	@GetMapping("/{cpf}")
	public ModelAndView show(@PathVariable String cpf) {
		Optional<Colaborador> optional = this.colaboradorRepository.findById(cpf);
		
		if(optional.isPresent()) {
			Colaborador colaborador = optional.get();
			ModelAndView mv = new ModelAndView("colaboradores/show");
			mv.addObject("colaborador",colaborador );
			return mv;
			
		}else {
			System.out.println("$$$$$$$$$$$$$$$$$$$$$$$$ NÃO ACHOU O COLABORADOR DO CPF" + cpf+ " $$$$$$$ ");
			return new  ModelAndView("redirect:/colaboradores");
		}
		
	}
	
	@GetMapping("/{cpf}/edit")
	public ModelAndView edit(@PathVariable String cpf, RequisicaoFormColaborador requisicao){
		Optional<Colaborador> optional= this.colaboradorRepository.findById(cpf);
		
		if(optional.isPresent()) {
			Colaborador colaborador = optional.get();
			requisicao.fromColaborador(colaborador);
			ModelAndView mv = new ModelAndView("colaboradores/edit");
			mv.addObject("colaboradorcpf", colaborador.getCpf());
			return mv;

			
			
		}else {
			System.out.println("$$$$$$$$$$$$$$$$$$$$$$$$ NÃO ACHOU O COLABORADOR DO CPF" + cpf+ " $$$$$$$ ");
			return new  ModelAndView("redirect:/colaboradores");
		}
		
	
	}
	
	@PostMapping("/{cpf}")
	public ModelAndView update(@PathVariable String cpf, @Valid RequisicaoFormColaborador requisicaoFormColaborador, BindingResult bindingResult ) {
		if(bindingResult.hasErrors()) {		
			ModelAndView mv = new ModelAndView("colaboradores/edit");
			mv.addObject("colaboradorcpf",cpf);
			return mv;
		}else {
			Optional<Colaborador> optional = this.colaboradorRepository.findById(cpf);
			if(optional.isPresent()) {
				Colaborador colaborador = requisicaoFormColaborador.toColaborador(optional.get());
				this.colaboradorRepository.save(colaborador);
				
				return new ModelAndView("redirect:/colaboradores/" + colaborador.getCpf());
				
			}else {
				System.out.println("$$$$$$$$$$$$$$$$$$$$$$$$ NÃO ACHOU O COLABORADOR DO CPF" + cpf+ " $$$$$$$ ");
				return new  ModelAndView("redirect:/colaboradores");
			}
		}
	}
	
	//*Web Parameter Tampering
		@PostMapping("")
		public ModelAndView create(@Valid RequisicaoFormColaborador requisicaoFormColaborador, BindingResult bindingResult) {
			if(bindingResult.hasErrors()) {
				System.out.println("***********************TEM ERROS");
				ModelAndView mv = new ModelAndView("colaboradores/new");
				return mv;
			}else {
			Colaborador colaborador = requisicaoFormColaborador.toColaborador();
			this.colaboradorRepository.save(colaborador);
			
			return new ModelAndView("redirect:/colaboradores/" + colaborador.getCpf());
			}
		}
	
		@GetMapping("/{cpf}/delete")
		public String delete(@PathVariable String cpf) {
			try {
				this.colaboradorRepository.deleteById(cpf);
				return ("redirect:/colaboradores");
			} catch (EmptyResultDataAccessException e) {
				 System.out.println(e);
				 return ("redirect:/colaboradores");  
				
			}
			
		}
		
}
