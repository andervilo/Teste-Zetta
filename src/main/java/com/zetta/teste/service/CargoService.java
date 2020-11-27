package com.zetta.teste.service;

import org.springframework.stereotype.Service;

import com.zetta.teste.arquitetura.service.GenericServiceimpl;
import com.zetta.teste.model.entity.Cargo;
import com.zetta.teste.repository.CargoRepository;

@Service
public class CargoService extends GenericServiceimpl<Cargo, CargoRepository>{

}
