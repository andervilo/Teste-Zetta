package com.zetta.teste.service;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.server.ResponseStatusException;

import com.zetta.teste.arquitetura.service.GenericServiceimpl;
import com.zetta.teste.model.entity.Cargo;
import com.zetta.teste.repository.CargoRepository;

@Service
public class CargoService extends GenericServiceimpl<Cargo, CargoRepository>{

	@Autowired
	private CargoRepository cargoRepository;
	
	@Override
	public Cargo create(@Valid @RequestBody Cargo cargo) {
		if(cargoRepository.existsByNome(cargo.getNome())) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Cargo já existe!");
		}
		return super.create(cargo);
	}
	
	public Cargo findByNome(String nome) {
		return cargoRepository.findByNome(nome)		;
	}
	
	public boolean existsByNome(String nome) {
		return cargoRepository.existsByNome(nome);
	}
}
