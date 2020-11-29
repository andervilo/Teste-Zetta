package com.zetta.teste.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.zetta.teste.arquitetura.controller.AbstractRestController;
import com.zetta.teste.model.entity.Cargo;
import com.zetta.teste.model.entity.Perfil;
import com.zetta.teste.service.PerfilService;
import com.zetta.teste.utils.UtilsExceptionMessage;

import io.swagger.annotations.Api;

@RestController
@RequestMapping("/api/perfis-usuarios")
@Api(tags = "Perfis de Usuários")
public class PerfilController extends AbstractRestController<Perfil, PerfilService>{
	
	@Autowired
	private PerfilService perfilService;

	@Override
	public ResponseEntity<HttpStatus> delete(Long id) {
		
		if(!perfilService.getRepository().existsById(id)) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, UtilsExceptionMessage.NOT_FOUND_ERROR);
		}
		
		if(perfilService.countUsuariossByPerfil(id) > 0) {
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, UtilsExceptionMessage.PERFIL_POSSUI_USUARIO_ERROR);
		}
		return super.delete(id);
	}
}
