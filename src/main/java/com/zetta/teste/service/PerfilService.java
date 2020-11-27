package com.zetta.teste.service;

import org.springframework.stereotype.Service;

import com.zetta.teste.arquitetura.service.GenericServiceimpl;
import com.zetta.teste.model.entity.Perfil;
import com.zetta.teste.repository.PerfilRepository;

@Service
public class PerfilService extends GenericServiceimpl<Perfil, PerfilRepository>{

}
