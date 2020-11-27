package com.zetta.teste.model.entity;

import java.time.LocalDate;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import com.zetta.teste.arquitetura.entity.BaseEntity;
import com.zetta.teste.enumeration.Sexo;

public abstract class Pessoa extends BaseEntity {

	private static final long serialVersionUID = 3177958610291783762L;

	private String nome;

	private String cpf;

	private LocalDate dataNascimento;

	@Enumerated(EnumType.STRING)
	private Sexo sexo;

	public Pessoa() {
		super();
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public LocalDate getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(LocalDate dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public Sexo getSexo() {
		return sexo;
	}

	public void setSexo(Sexo sexo) {
		this.sexo = sexo;
	}

}
