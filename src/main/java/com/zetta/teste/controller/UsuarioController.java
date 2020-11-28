package com.zetta.teste.controller;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zetta.teste.arquitetura.controller.IRestController;
import com.zetta.teste.model.dto.UsuarioInputDTO;
import com.zetta.teste.model.dto.UsuarioOutputDTO;
import com.zetta.teste.model.entity.Usuario;
import com.zetta.teste.service.UsuarioService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api/usuarios")
@Api(tags = "Usuários")
public class UsuarioController implements IRestController<UsuarioInputDTO>{
	
	ModelMapper mapper = new ModelMapper();
	
	@Autowired
	private UsuarioService usuarioService;

	@GetMapping("")
	@ApiOperation(value = "Obter lista.")
	public ResponseEntity<Page<UsuarioOutputDTO>> listAll(Pageable pageable) {
		Page<UsuarioOutputDTO> entities = usuarioService.findAll(pageable)
				 .map((usuario -> mapper.map(usuario, UsuarioOutputDTO.class)));
		
		return ResponseEntity.ok(entities);
	}

	@GetMapping("/{id}")
	@ApiOperation(value = "Obter por ID.")
	public ResponseEntity<UsuarioOutputDTO> showById(@PathVariable Long id) {
		if(!usuarioService.getRepository().existsById(id)) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		Usuario usuario = usuarioService.findById(id);
		
		UsuarioOutputDTO funcionarioOutputDTO = mapper.map(usuario, UsuarioOutputDTO.class);
		
		return ResponseEntity.ok(funcionarioOutputDTO);
	}

	@PostMapping(value="")
	@ApiOperation(value = "Criar.")
	public ResponseEntity<UsuarioOutputDTO> create(@Valid @RequestBody UsuarioInputDTO usuarioInputDTO) {
		Usuario usuario = mapper.map(usuarioInputDTO, Usuario.class);
		
		usuario = usuarioService.create(usuario);
		
		if(usuario == null) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		UsuarioOutputDTO suarioOutputDTO = mapper.map(usuario, UsuarioOutputDTO.class);
		
		return ResponseEntity.ok(suarioOutputDTO);
	}

	@PutMapping(value="/{id}")
	@ApiOperation(value = "Alterar.")
	public ResponseEntity<UsuarioOutputDTO> update(@PathVariable Long id, @RequestBody UsuarioInputDTO usuarioInputDTO) {
		if(!usuarioService.getRepository().existsById(id)) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		Usuario usuarioToUpdate = usuarioService.findById(id);
		
		Usuario usuario = mapper.map(usuarioInputDTO, Usuario.class);
		
		usuario.setId(usuarioToUpdate.getId());
		
		usuario.getPerfis().addAll(usuarioToUpdate.getPerfis());
		
		usuario = usuarioService.update(id, usuario);
		
		if(usuario == null) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		UsuarioOutputDTO usuarioOutputDTO = mapper.map(usuario, UsuarioOutputDTO.class);
		
		return ResponseEntity.ok(usuarioOutputDTO);
	
	}

	@DeleteMapping("/{id}")
	@ApiOperation(value = "Excluir.")
	public ResponseEntity<?> delete(@PathVariable Long id) {
		if(!usuarioService.getRepository().existsById(id)) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		if(!usuarioService.delete(id)) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}		
		
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
}
