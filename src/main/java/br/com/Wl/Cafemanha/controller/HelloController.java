package br.com.Wl.Cafemanha.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HelloController {
	
	@GetMapping("/hello-servelet")
	public String hello(HttpServletRequest request) {
		request.setAttribute("nome", "Matheus");
		return "hello"; //O spring vai rederizar o arquivo que esta na pasta templetes/hello.html
	}
	
	@GetMapping("/hello")
	public String hello(Model model) {
		model.addAttribute("nome", "Raul");
		return "hello"; //O spring vai rederizar o arquivo que esta na pasta templetes/hello.html
	}
	
	@GetMapping("/hello-model")
	public ModelAndView hello() {
		ModelAndView mv = new ModelAndView("hello"); //Nome do arquivo html a ser redenrizado
		mv.addObject("nome", "Marcio");
		return mv; //O spring vai rederizar o arquivo que esta na pasta templetes/hello.html
	}

}
