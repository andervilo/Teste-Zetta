package com.zetta.teste.model.dto;

import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.zetta.teste.arquitetura.dto.IEntityDTO;
import com.zetta.teste.enumeration.Sexo;

import io.swagger.annotations.ApiModelProperty;

public class UsuarioOutputDTO implements IEntityDTO {

	private static final long serialVersionUID = 749754216395872682L;

	@ApiModelProperty(position = 1)
	private Long id;

	@ApiModelProperty(position = 2)
	private String nome;

	@ApiModelProperty(position = 3)
	private String cpf;

	@ApiModelProperty(position = 4)
	private Sexo sexo;

	//@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
	@ApiModelProperty(position = 5)
	private LocalDate dataNascimento;

	@ApiModelProperty(position = 6)
	private String cargoNome;

	@ApiModelProperty(position = 7)
	private List<PerfilOutputDTO> perfis;

	public UsuarioOutputDTO() {

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

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getSexo() {
		return sexo.getDescricao();
	}

	public void setSexo(Sexo sexo) {
		this.sexo = sexo;
	}

	public LocalDate getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(LocalDate dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public String getCargoNome() {
		return cargoNome;
	}

	public void setCargoNome(String cargoNome) {
		this.cargoNome = cargoNome;
	}

	public List<PerfilOutputDTO> getPerfis() {
		return perfis;
	}

	public void setPerfis(List<PerfilOutputDTO> perfis) {
		this.perfis = perfis;
	}

}
