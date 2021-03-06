package com.zetta.teste.model.entity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Usuario extends Pessoa {

	private static final long serialVersionUID = -391428602278473239L;

	@ManyToOne(optional = false)
	private Cargo cargo;

	@Column(nullable = false)
	private LocalDate dataCadastro;

	@JsonIgnore
	@ManyToMany
	@JoinTable(name = "usuario_perfil", joinColumns = @JoinColumn(name = "usuario_id"), inverseJoinColumns = @JoinColumn(name = "perfil_id"))
	private List<Perfil> perfis = new ArrayList<>();

	public Usuario() {

	}

	public Cargo getCargo() {
		return cargo;
	}

	public void setCargo(Cargo cargo) {
		this.cargo = cargo;
	}

	public List<Perfil> getPerfis() {
		return perfis;
	}

	public void setPerfis(List<Perfil> perfis) {		
		this.perfis = perfis.stream().filter((Perfil perfil)->{
			return !this.perfis.contains(perfil);
		}).collect(Collectors.toList());
	}

	public LocalDate getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(LocalDate dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

}
