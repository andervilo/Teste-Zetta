package com.zetta.teste.service;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.server.ResponseStatusException;

import com.zetta.teste.arquitetura.service.GenericServiceimpl;
import com.zetta.teste.model.entity.Perfil;
import com.zetta.teste.repository.PerfilRepository;
import com.zetta.teste.utils.UtilsExceptionMessage;

@Service
public class PerfilService extends GenericServiceimpl<Perfil, PerfilRepository>{
	@Autowired
	private PerfilRepository perfilRepository;
	
	@Override
	public Perfil create(@Valid @RequestBody Perfil perfil) {
		if(perfilRepository.existsByNome(perfil.getNome())) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, UtilsExceptionMessage.PERFIL_EXISTENTE_ERROR);
		}
		return super.create(perfil);
	}
	
	public Perfil findByNome(String nome) {
		return perfilRepository.findByNome(nome);
	}
	
	public boolean existsByNome(String nome) {
		return perfilRepository.existsByNome(nome);
	}
	
	public int countUsuariossByPerfil(Long id) {
		return super.findById(id).getUsuarios().size();
	}

}
