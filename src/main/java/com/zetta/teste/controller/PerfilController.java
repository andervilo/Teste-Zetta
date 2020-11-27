package com.zetta.teste.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zetta.teste.arquitetura.controller.AbstractRestController;
import com.zetta.teste.model.entity.Perfil;
import com.zetta.teste.service.PerfilService;

import io.swagger.annotations.Api;

@RestController
@RequestMapping("/api/perfis-usuarios")
@Api(tags = "Perfis de Usuários")
public class PerfilController extends AbstractRestController<Perfil, PerfilService>{

}
