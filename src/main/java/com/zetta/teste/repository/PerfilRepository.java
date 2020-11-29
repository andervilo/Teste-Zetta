package com.zetta.teste.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.zetta.teste.model.entity.Perfil;

public interface PerfilRepository extends JpaRepository<Perfil, Long>{
	boolean existsByNome(String nome);
	
	Perfil findByNome(String nome);
}
