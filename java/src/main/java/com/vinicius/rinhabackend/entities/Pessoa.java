package com.vinicius.rinhabackend.entities;

import java.time.LocalDate;
import java.util.UUID;

import com.vinicius.rinhabackend.dtos.PessoaInsertDTO;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity(name = "pessoas")
public class Pessoa {
	@Id
	private UUID id;
	private String apelido;
	private String nome;
	private LocalDate nascimento;
	private String[] stack;
	
	public Pessoa(PessoaInsertDTO dto) {
		this.id = UUID.randomUUID();
		this.apelido = dto.getApelido();
		this.nome = dto.getNome();
		this.nascimento = dto.getNascimento();
		this.stack = dto.getStack();
	}
	
	public Pessoa(UUID id, String apelido, String nome, LocalDate nascimento, String[] stack) {
		this.id = id;
		this.apelido = apelido;
		this.nome = nome;
		this.nascimento = nascimento;
		this.stack = stack;
	}
	
	public Pessoa() {}

	public UUID getId() {
		return id;
	}
	public void setId(UUID id) {
		this.id = id;
	}
	public String getApelido() { 
		return apelido;
	}
	public void setApelido(String apelido) {
		this.apelido = apelido;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public LocalDate getNascimento() {
		return nascimento;
	}
	public void setNascimento(LocalDate nascimento) {
		this.nascimento = nascimento;
	}
	public String[] getStack() {
		return stack;
	}
	public void setStack(String[] stack) {
		this.stack = stack;
	}
}
