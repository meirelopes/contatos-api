package com.meirelopes.classes;

import java.util.ArrayList;

import org.springframework.web.bind.annotation.GetMapping;


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



}
