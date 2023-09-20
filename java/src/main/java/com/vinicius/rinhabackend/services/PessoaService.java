package com.vinicius.rinhabackend.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vinicius.rinhabackend.dtos.PessoaInsertDTO;
import com.vinicius.rinhabackend.entities.Pessoa;
import com.vinicius.rinhabackend.exceptions.CustomValidationException;
import com.vinicius.rinhabackend.exceptions.ResourceAlreadyExists;
import com.vinicius.rinhabackend.exceptions.ResourceNotFoundException;
import com.vinicius.rinhabackend.repositories.PessoaRepository;

@Service
public class PessoaService {

	@Autowired
	PessoaRepository repository;
	
	public Pessoa findById(UUID id) {
		Optional<Pessoa> object = repository.findById(id);
		Pessoa entity = object.orElseThrow(() -> new ResourceNotFoundException());
		return entity;
	}
	
	public List<Pessoa> findByTerm(String term) {
		if (term.length() == 0) throw new CustomValidationException();
		return repository.findByTerm(term);
	}
	
	public Long getContagem() {
		return repository.count();
	}
	
	public Pessoa save(PessoaInsertDTO dto) {
		try {
			Pessoa entity = repository.save(new Pessoa(dto));
			return entity;
		} catch (Exception e) {
			String classCause = e.getCause().getClass().getSimpleName();
			if (classCause == "ConstraintViolationException") throw new ResourceAlreadyExists();
			throw new CustomValidationException();
		}
	}
}
