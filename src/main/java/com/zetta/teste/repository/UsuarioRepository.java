package com.zetta.teste.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.zetta.teste.model.entity.Cargo;
import com.zetta.teste.model.entity.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>{

	Usuario findByCpf(String cpf);
	
	boolean existsByCpf(String cpf);
	
	int countByCargo(Cargo cargo);
}
