package com.zetta.teste.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.zetta.teste.arquitetura.controller.AbstractRestController;
import com.zetta.teste.model.entity.Cargo;
import com.zetta.teste.service.CargoService;
import com.zetta.teste.service.UsuarioService;

import io.swagger.annotations.Api;

@RestController
@RequestMapping("/api/cargos")
@Api(tags = "Cargos")
public class CargoController extends AbstractRestController<Cargo, CargoService>{
	
	@Autowired
	private UsuarioService  usuarioService;
	
	@Autowired
	private CargoService cargoService;
	
	@Override
	public ResponseEntity<HttpStatus> delete(Long id) {
		Cargo cargo = cargoService.findById(id);
		
		if(cargo == null) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Recurso não encontrado!");
		}
		
		if(usuarioService.countByCargo(cargo) > 0) {
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Cargo possui Usuários relacionados e não pode ser excluido!");
		}
		return super.delete(id);
	}

}
