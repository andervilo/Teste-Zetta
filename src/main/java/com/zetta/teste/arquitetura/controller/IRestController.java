package com.zetta.teste.arquitetura.controller;

import java.io.Serializable;

import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;


public interface IRestController<E extends Serializable> {	
	public ResponseEntity<?> listAll(Pageable pageable);
	public ResponseEntity<?> showById(@PathVariable(value = "id") Long id);	
	public ResponseEntity<?> create(@RequestBody E object);	
	public ResponseEntity<?> update(@PathVariable(value = "id") Long id, @RequestBody E object);	
	public ResponseEntity<?> delete(@PathVariable(value = "id") Long id);

}
