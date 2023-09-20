package com.vinicius.rinhabackend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vinicius.rinhabackend.services.PessoaService;

@RestController
@RequestMapping(value = "/contagem-pessoas")
public class ContagemPessoasController {
	
	@Autowired
	PessoaService service;
	@GetMapping
	public ResponseEntity<Long> getTotalPessoas() {
		return ResponseEntity.ok().body(service.getContagem());
	}
}
