package com.zetta.teste.service;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.server.ResponseStatusException;

import com.zetta.teste.arquitetura.service.GenericServiceimpl;
import com.zetta.teste.model.entity.Cargo;
import com.zetta.teste.model.entity.Usuario;
import com.zetta.teste.repository.UsuarioRepository;
import com.zetta.teste.utils.UtilsExceptionMessage;

@Service
public class UsuarioService extends GenericServiceimpl<Usuario, UsuarioRepository>{
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Override
	public Usuario create(@Valid @RequestBody Usuario usuario) {
		if(usuarioRepository.existsByCpf(usuario.getCpf())) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, UtilsExceptionMessage.CPF_EXISTENTE_ERROR);
		}
		return super.create(usuario);
	}
	
	public Usuario findByCpf(String cpf) {
		return usuarioRepository.findByCpf(cpf);
	}
	
	public boolean existsByCpf(String cpf) {
		return usuarioRepository.existsByCpf(cpf);
	}

	public int countByCargo(Cargo cargo) {
		return usuarioRepository.countByCargo(cargo);
	}
}
