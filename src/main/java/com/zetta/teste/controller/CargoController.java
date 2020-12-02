package com.zetta.teste.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.zetta.teste.arquitetura.controller.AbstractRestController;
import com.zetta.teste.model.entity.Cargo;
import com.zetta.teste.service.CargoService;
import com.zetta.teste.service.UsuarioService;
import com.zetta.teste.utils.UtilsExceptionMessage;

import io.swagger.annotations.Api;

@RestController
@CrossOrigin(origins = "*")
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
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, UtilsExceptionMessage.NOT_FOUND_ERROR);
		}
		
		if(usuarioService.countByCargo(cargo) > 0) {
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, UtilsExceptionMessage.CARGO_POSSUI_USUARIO_ERROR);
		}
		return super.delete(id);
	}

}
