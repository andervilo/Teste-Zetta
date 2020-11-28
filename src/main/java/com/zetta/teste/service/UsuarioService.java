package com.zetta.teste.service;

import org.springframework.stereotype.Service;

import com.zetta.teste.arquitetura.service.GenericServiceimpl;
import com.zetta.teste.model.entity.Usuario;
import com.zetta.teste.repository.UsuarioRepository;

@Service
public class UsuarioService extends GenericServiceimpl<Usuario, UsuarioRepository>{

}
