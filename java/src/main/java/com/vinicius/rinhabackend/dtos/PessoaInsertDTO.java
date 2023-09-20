package com.vinicius.rinhabackend.dtos;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import com.vinicius.rinhabackend.exceptions.CustomEmptyException;
import com.vinicius.rinhabackend.exceptions.CustomValidationException;

import jakarta.validation.constraints.Size;

public class PessoaInsertDTO {

	@Size(max = 32)
	private String apelido;
	@Size(max = 100)
	private String nome;
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
	private LocalDate nascimento;
	private String[] stack;
	
	
	
	public PessoaInsertDTO(String apelido, String nome, String nascimento, String[] stack) {
		if (nome == null || apelido == null || nascimento == null ) throw new CustomEmptyException();
		this.setApelido(apelido);
		this.setNome(nome);
		this.setNascimento(nascimento);
		this.setStack(stack);	
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
		 try {
	            Integer.parseInt(nome);
	            throw new CustomValidationException();
	        } catch (NumberFormatException e) {
	            this.nome = nome;
	        }
	}
	public LocalDate getNascimento() {
		return this.nascimento;
	}
	public void setNascimento(String nascimento) {
		int year = Integer.parseInt(nascimento.substring(0, 4));
		int month = Integer.parseInt(nascimento.substring(5, 7));
		int day = Integer.parseInt(nascimento.substring(8, 10));
		this.nascimento = LocalDate.of(year, month, day);
	}
	public String[] getStack() {
		return stack;
	}
	public void setStack(String[] stack) {
		for (String item : stack) {
			try {
	            Integer.parseInt(item);
	            throw new CustomValidationException();
	        } catch (NumberFormatException e) {
	        }
			if (item.length() > 32) throw new CustomValidationException();
        }
		this.stack = stack;
	}
	
	
}
