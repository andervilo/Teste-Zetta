package com.zetta.teste.arquitetura.controller;

import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.server.ResponseStatusException;

import com.zetta.teste.arquitetura.entity.BaseEntity;
import com.zetta.teste.arquitetura.service.GenericService;
import com.zetta.teste.utils.UtilsExceptionMessage;

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
		if (!service.getRepository().existsById(id)) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, UtilsExceptionMessage.NOT_FOUND_ERROR);
		}
		E entity = (E) service.findById(id);
		return (ResponseEntity<E>) ResponseEntity.ok().body(entity);
	}

	@SuppressWarnings("unchecked")
	@PostMapping("/")
	@ApiOperation(value = "Criar.")
	@Override
	public ResponseEntity<E> create(@Valid @RequestBody E object) {
		
		E entity = (E) service.create(object);

		if (entity == null) {
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, UtilsExceptionMessage.SERVER_ERROR);
		}

		return ResponseEntity.ok().body(entity);
	}

	@SuppressWarnings("unchecked")
	@PutMapping("/{id}")
	@ApiOperation(value = "Alterar.")
	@Override
	public ResponseEntity<E> update(Long id, @Valid E object) {
		if (!service.getRepository().existsById(id)) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, UtilsExceptionMessage.NOT_FOUND_ERROR);
		}

		E entity = (E) service.update(id, object);

		if (entity == null) {
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, UtilsExceptionMessage.SERVER_ERROR);
		}

		return ResponseEntity.ok().body(entity);
	}

	@SuppressWarnings("unchecked")
	@DeleteMapping("/{id}")
	@ApiOperation(value = "Excluir.")
	@Override
	public ResponseEntity<HttpStatus> delete(@PathVariable Long id) {
		
		if (!service.getRepository().existsById(id)) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, UtilsExceptionMessage.NOT_FOUND_ERROR);
		}
		
		if(!service.delete(id)) {
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, UtilsExceptionMessage.SERVER_ERROR);
		}
		
		return new ResponseEntity<>(HttpStatus.OK);
	}

	public S getService() {
		return service;
	}
	
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public Map<String, Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
		Map<String, Map<String, String>> errors = new HashMap<>();
		Map<String, String> errorsBody = new HashMap<>();

		ex.getBindingResult().getAllErrors().forEach((error) -> {

			String fieldName = ((FieldError) error).getField();

			String errorMessage = error.getDefaultMessage();

			errorsBody.put(fieldName, errorMessage);
		});
		errors.put("fieldErrors", errorsBody);
		return errors;
	}

}
