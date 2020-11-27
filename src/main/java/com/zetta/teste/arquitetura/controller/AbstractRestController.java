package com.zetta.teste.arquitetura.controller;

import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

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
import org.springframework.web.server.ResponseStatusException;

import com.zetta.teste.arquitetura.entity.BaseEntity;
import com.zetta.teste.arquitetura.service.GenericService;

import io.swagger.annotations.ApiOperation;

@SuppressWarnings("rawtypes")
public abstract class AbstractRestController<E extends BaseEntity, S extends GenericService>
		implements IRestController<E> {
	@Autowired
	private S service;

	@SuppressWarnings("unchecked")
	@GetMapping("/")
	@ApiOperation(value = "Obter Lista.")
	@Override
	public ResponseEntity<?> listAll(Pageable pageable) {
		Page<E> page = (Page<E>) service.findAll(pageable);
		return ResponseEntity.ok().body(page);
	}

	@SuppressWarnings("unchecked")
	@GetMapping("/{id}")
	@ApiOperation(value = "Obter por ID.")
	@Override
	public ResponseEntity<?> showById(@PathVariable Long id) {
		E entity = (E) service.findById(id);
		if (entity == null) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Recurso não encontrado!");
		}
		return (ResponseEntity<E>) ResponseEntity.ok().body(entity);
	}

	@SuppressWarnings("unchecked")
	@PostMapping("/")
	@ApiOperation(value = "Criar.")
	@Override
	public ResponseEntity<E> create(@Valid @RequestBody E object) {

		if (object == null) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Requisição feita com objeto nulo!");
		}

		E entity = (E) service.create(object);

		if (entity == null) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Não foi possível executar esta operação!");
		}

		return ResponseEntity.ok().body(entity);
	}

	@SuppressWarnings("unchecked")
	@PutMapping("/{id}")
	@ApiOperation(value = "Alterar.")
	@Override
	public ResponseEntity<E> update(Long id, @Valid E object) {
		if (object == null) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Requisição feita com objeto nulo!");
		}

		E entity = (E) service.update(id, object);

		if (entity == null) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Não foi possível executar esta operação!");
		}

		return ResponseEntity.ok().body(entity);
	}

	@SuppressWarnings("unchecked")
	@DeleteMapping("/{id}")
	@ApiOperation(value = "Excluir.")
	@Override
	public ResponseEntity<Map<String, String>> delete(@PathVariable Long id) {
		Map<String, String> map = new HashMap<String, String>();
		if (!service.getRepository().existsById(id)) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Recurso não encontrado!");
		}

		try {
			service.delete(id);
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
		}
		map.put("message", "Recurso excuido!");
		return ResponseEntity.ok((new HashMap<String, String>()));
	}

	public S getService() {
		return service;
	}

}
