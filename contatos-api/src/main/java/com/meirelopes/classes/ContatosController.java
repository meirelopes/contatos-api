package com.meirelopes.classes;

import java.util.ArrayList;
import java.util.UUID;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ContatosController {
	private static final ArrayList<Contato> LISTA_CONTATOS = new ArrayList<>();
	
	static {
		LISTA_CONTATOS.add(new Contato("01", "João", "9999-0001"));
		LISTA_CONTATOS.add(new Contato("02", "Felipe", "9999-0002"));
		LISTA_CONTATOS.add(new Contato("03", "Fernanda", "9999-0003"));
		LISTA_CONTATOS.add(new Contato("04", "Lívia", "9999-0004"));
		LISTA_CONTATOS.add(new Contato("05", "Carolina", "9999-0005"));
		LISTA_CONTATOS.add(new Contato("06", "Maria", "9999-0006"));
		
	}
	
	@GetMapping("/")
	public String index() {
		return "index";
	}
	


	@GetMapping("/contatos")
	public ModelAndView listar() {
		ModelAndView modelAndView = new ModelAndView("listar");
		
		modelAndView.addObject("contatos", LISTA_CONTATOS);
		
		return modelAndView;
	}
	
	@GetMapping("/contatos/novo")
	public ModelAndView novo() {
		ModelAndView modelAndView = new ModelAndView("formulario");
		return modelAndView.addObject("contato", new Contato());
	}
	
	@PostMapping("/contatos")
	public String cadastrar(Contato contato) {
		String id = UUID.randomUUID().toString();
		contato.setId(id);
		LISTA_CONTATOS.add(contato);
		
		return "redirect:/contatos";	
	}
	
	@GetMapping("/contatos/{id}/editar")
	public ModelAndView editar(@PathVariable String id) {
		ModelAndView modelAndView = new ModelAndView("formulario");
		Contato contato = procurarContato(id);

		 modelAndView.addObject("contato", contato);
		 return modelAndView;
	}
	
	
	@PutMapping("/contatos/{id}")
	public String atualizar(Contato contato) {
		Integer indice = procurarIndiceContato(contato.getId());

		Contato contatoVelho = LISTA_CONTATOS.get(indice);
		
		LISTA_CONTATOS.remove(contatoVelho);
		LISTA_CONTATOS.add(indice, contato);
		return "redirect:/contatos";	
	}
	

	
	//Métodos auxiliares -------------------------------
	private Contato procurarContato(String id) {
		Integer indice = procurarIndiceContato(id);
		if(indice!=null) {
			Contato contato = LISTA_CONTATOS.get(indice);
			return contato;
		}
		return null;
	}
	
	private Integer procurarIndiceContato(String id) {
		for (int  i = 0; i < LISTA_CONTATOS.size(); i++) {
			Contato contato = LISTA_CONTATOS.get(i);
			
			if(contato.getId().equals(id)) {
				return i;
			} 
		}
		return null;
	}

	@DeleteMapping("/contatos/{id}")
	public String remover(@PathVariable String id) {
		Contato contato = procurarContato(id);
		LISTA_CONTATOS.remove(contato);
		return "redirect:/contatos";	
	}
}

