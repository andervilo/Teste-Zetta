package com.zetta.teste.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zetta.teste.arquitetura.controller.AbstractRestController;
import com.zetta.teste.model.entity.Cargo;
import com.zetta.teste.service.CargoService;

import io.swagger.annotations.Api;

@RestController
@RequestMapping("/api/cargos")
@Api(tags = "Cargos")
public class CargoController extends AbstractRestController<Cargo, CargoService>{

}
