package com.zetta.teste.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;

import com.zetta.teste.arquitetura.entity.BaseEntity;

@Entity
public class Cargo extends BaseEntity {

	private static final long serialVersionUID = 1315602694749480017L;
	
	@Column(unique = true)
	private String nome;

	public Cargo() {

	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

}
