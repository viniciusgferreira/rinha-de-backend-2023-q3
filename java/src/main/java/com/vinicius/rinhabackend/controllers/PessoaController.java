package com.vinicius.rinhabackend.controllers;

import java.net.URI;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.vinicius.rinhabackend.dtos.PessoaInsertDTO;
import com.vinicius.rinhabackend.entities.Pessoa;
import com.vinicius.rinhabackend.services.PessoaService;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;

@RestController
@RequestMapping(value = "/pessoas")
public class PessoaController {
	
	@Autowired
	PessoaService service;
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Pessoa> findById(@PathVariable @NotBlank @org.hibernate.validator.constraints.UUID UUID id) {
		return ResponseEntity.ok().body(service.findById(id));
	}
	
	@PostMapping
	public ResponseEntity<String> insertPessoa(@Valid @RequestBody PessoaInsertDTO dto){
		Pessoa entity = service.save(dto);
		URI location = URI.create("/pessoas/" + entity.getId());
		return ResponseEntity.created(location).build();
	}
	
	@GetMapping
	public ResponseEntity<List<Pessoa>> findByTerm(@NotEmpty @RequestParam(name = "t", required = true) String term) {
		List<Pessoa> result = service.findByTerm(term);
		return ResponseEntity.ok().body(result);
	}

}
