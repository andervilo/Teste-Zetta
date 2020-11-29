package com.zetta.teste;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.server.ResponseStatusException;

import com.zetta.teste.model.entity.Cargo;
import com.zetta.teste.model.entity.Perfil;
import com.zetta.teste.model.entity.Usuario;
import com.zetta.teste.service.CargoService;
import com.zetta.teste.service.PerfilService;
import com.zetta.teste.service.UsuarioService;

@SpringBootTest
class TesteZettaApplicationTests {
	
	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired
	private PerfilService perfilService;
	
	@Autowired
	private CargoService cargoService;

	private final String CPF = "013.456.780-37";
	
	private final String CARGO = "Analista";
	
	private final String PERFIL_USER = "USER";

	@Test
	void buscarUsuarioPorCpfTest() {
		Usuario usuario = usuarioService.findByCpf(CPF);
		assertEquals(CPF, usuario.getCpf());
	}
	
	@Test
	void existeUsuarioPorCpfTest() {
		assertTrue(usuarioService.existsByCpf(CPF));
	}
	
	@Test
	void buscarPerfilPorNomeTest() {
		Perfil perfil = perfilService.findByNome(PERFIL_USER);
		assertEquals(PERFIL_USER, perfil.getNome());
	}
	
	@Test
	void existePerfilPorNomeTest() {
		assertTrue(perfilService.existsByNome(PERFIL_USER));
	}
		
	@Test
	void InsertPerfilExistenteTest() {
		Perfil perfil = new Perfil();
		perfil.setNome(PERFIL_USER);
		Throwable exceptionThatWasThrown = assertThrows(ResponseStatusException.class, () -> {
			perfilService.create(perfil);
		});

		assertTrue(exceptionThatWasThrown.getMessage().contains("Perfil já existe!"));
	}
	
	@Test
	void buscarCargoPorNomeTest() {
		Cargo cargo = cargoService.findByNome(CARGO);
		assertEquals(CARGO, cargo.getNome());
	}
	
	@Test
	void existeCargoPorNomeTest() {
		assertTrue(cargoService.existsByNome(CARGO));
	}
	
	@Test
	void InsertCargoExistenteTest() {
		Cargo cargo = new Cargo();
		cargo.setNome(CARGO);
		Throwable exceptionThatWasThrown = assertThrows(ResponseStatusException.class, () -> {
			cargoService.create(cargo);
		});

		assertTrue(exceptionThatWasThrown.getMessage().contains("Cargo já existe!"));
	}
	
	@Test
	void countTest() {
		Cargo cargo = cargoService.findById(new Long(1));
		System.out.println(usuarioService.countByCargo(cargo));
	}

}
