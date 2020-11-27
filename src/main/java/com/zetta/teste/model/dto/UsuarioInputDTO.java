package com.zetta.teste.model.dto;

import java.time.LocalDate;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.br.CPF;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.zetta.teste.arquitetura.dto.IEntityDTO;
import com.zetta.teste.enumeration.Sexo;

import io.swagger.annotations.ApiModelProperty;

public class UsuarioInputDTO implements IEntityDTO{

	private static final long serialVersionUID = 749754216395872682L;

	@ApiModelProperty(position = 1, required = true)
	@NotNull(message = "Campo id é obrigatório!")
	@JsonProperty(required = true)
	private Long id;

	@ApiModelProperty(position = 2, required = true, example = "Dom Pedro II")
	@NotBlank(message = "Campo nome é obrigatório!")
	@NotNull(message = "Campo nome é obrigatório!")
	private String nome;

	@ApiModelProperty(position = 3, required = true, example = "999.999.999-99")
	@NotBlank(message = "Campo cpf é obrigatório!")
	@NotNull(message = "Campo cpf é obrigatório!")
	@CPF(message = "CPF Inválido")
	private String cpf;

	@ApiModelProperty(position = 4, required = true, example = "M-Masculino/F-Feminino")
	@NotBlank(message = "Campo sexo é obrigatório!")
	@NotNull(message = "Campo sexo é obrigatório!")
	private Sexo sexo;

	@ApiModelProperty(position = 5, dataType = "LocalDate", example = "2000-10-15", required = true)
	@NotNull(message = "Campo dataNascimento é obrigatório!")
	private LocalDate dataNascimento;

	@ApiModelProperty(position = 6, required = true)
	@NotNull(message = "Campo cargoId é obrigatório!")
	private Long cargoId;

	public UsuarioInputDTO() {

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

	public Sexo getSexo() {
		return sexo;
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

	public Long getCargoId() {
		return cargoId;
	}

	public void setCargoId(Long cargoId) {
		this.cargoId = cargoId;
	}

}
