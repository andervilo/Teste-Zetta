package com.zetta.teste.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.zetta.teste.arquitetura.entity.BaseEntity;

@Entity
public class Cargo extends BaseEntity {

	private static final long serialVersionUID = 1315602694749480017L;
	
	@NotNull(message = "Campo NOME é obrigatório")
	@NotBlank(message = "Campo NOME é obrigatório")
	@Column(unique = true, nullable = false)
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
