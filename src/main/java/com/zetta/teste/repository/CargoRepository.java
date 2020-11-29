package com.zetta.teste.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.zetta.teste.model.entity.Cargo;

public interface CargoRepository extends JpaRepository<Cargo, Long>{
	
	boolean existsByNome(String nome);
	Cargo findByNome(String nome);
}
