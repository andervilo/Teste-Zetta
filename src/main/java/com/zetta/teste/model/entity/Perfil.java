package com.zetta.teste.model.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.zetta.teste.arquitetura.entity.BaseEntity;

@Entity
public class Perfil extends BaseEntity {

	private static final long serialVersionUID = 1315602694749480017L;

	@Column(unique = true)
	private String nome;

	@JsonIgnore
	@ManyToMany(mappedBy = "perfis")
	private List<Usuario> usuarios;

	public Perfil() {

	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public List<Usuario> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

}
