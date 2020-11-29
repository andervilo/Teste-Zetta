package com.zetta.teste.model.dto;

import com.zetta.teste.arquitetura.dto.IEntityDTO;

public class PerfilOutputDTO implements IEntityDTO {

	private static final long serialVersionUID = -8166977086865529880L;

	private Long id;

	private String nome;

	public PerfilOutputDTO() {

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

}
