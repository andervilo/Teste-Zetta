package com.zetta.teste.controller;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.zetta.teste.arquitetura.controller.IRestController;
import com.zetta.teste.model.dto.UsuarioInputDTO;
import com.zetta.teste.model.dto.UsuarioOutputDTO;
import com.zetta.teste.model.entity.Usuario;
import com.zetta.teste.service.UsuarioService;
import com.zetta.teste.utils.UtilsExceptionMessage;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/usuarios")
@Api(tags = "Usuários")
public class UsuarioController implements IRestController<UsuarioInputDTO> {

	ModelMapper mapper = new ModelMapper();

	@Autowired
	private UsuarioService usuarioService;

	@GetMapping("")
	@ApiOperation(value = "Obter lista.")
	public ResponseEntity<Page<UsuarioOutputDTO>> listAll(Pageable pageable) {
		Page<UsuarioOutputDTO> entities = usuarioService.findAll(pageable)
				.map((usuario -> mapper.map(usuario, UsuarioOutputDTO.class)));

		return ResponseEntity.ok(entities);
	}

	@GetMapping("/{id}")
	@ApiOperation(value = "Obter por ID.")
	public ResponseEntity<UsuarioOutputDTO> showById(@PathVariable Long id) {
		if (!usuarioService.getRepository().existsById(id)) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, UtilsExceptionMessage.NOT_FOUND_ERROR);
		}

		Usuario usuario = usuarioService.findById(id);

		UsuarioOutputDTO funcionarioOutputDTO = mapper.map(usuario, UsuarioOutputDTO.class);

		return ResponseEntity.ok(funcionarioOutputDTO);
	}

	@PostMapping(value = "")
	@ApiOperation(value = "Criar.")
	public ResponseEntity<UsuarioOutputDTO> create(@Valid @RequestBody UsuarioInputDTO usuarioInputDTO) {
		Usuario usuario = mapper.map(usuarioInputDTO, Usuario.class);
		usuario.setDataCadastro(LocalDate.now());
		usuario = usuarioService.create(usuario);

		if (usuario == null) {
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, UtilsExceptionMessage.SERVER_ERROR);
		}

		UsuarioOutputDTO suarioOutputDTO = mapper.map(usuario, UsuarioOutputDTO.class);

		return ResponseEntity.ok(suarioOutputDTO);
	}

	@PutMapping(value = "/{id}")
	@ApiOperation(value = "Alterar.")
	public ResponseEntity<UsuarioOutputDTO> update(@PathVariable Long id,
			@Valid @RequestBody UsuarioInputDTO usuarioInputDTO) {
		if (!usuarioService.getRepository().existsById(id)) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, UtilsExceptionMessage.NOT_FOUND_ERROR);
		}

		Usuario usuarioToUpdate = usuarioService.findById(id);

		Usuario usuario = mapper.map(usuarioInputDTO, Usuario.class);

		usuario.setId(usuarioToUpdate.getId());

		usuario.getPerfis().addAll(usuarioToUpdate.getPerfis());

		usuario.setDataCadastro(usuarioToUpdate.getDataCadastro());

		usuario = usuarioService.update(id, usuario);

		if (usuario == null) {
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, UtilsExceptionMessage.SERVER_ERROR);
		}

		UsuarioOutputDTO usuarioOutputDTO = mapper.map(usuario, UsuarioOutputDTO.class);

		return ResponseEntity.ok(usuarioOutputDTO);

	}

	@DeleteMapping("/{id}")
	@ApiOperation(value = "Excluir.")
	public ResponseEntity<?> delete(@PathVariable Long id) {
		if (!usuarioService.getRepository().existsById(id)) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, UtilsExceptionMessage.NOT_FOUND_ERROR);
		}

		if (!usuarioService.delete(id)) {
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, UtilsExceptionMessage.SERVER_ERROR);
		}

		return new ResponseEntity<>(HttpStatus.OK);
	}

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public Map<String, Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
		Map<String, Map<String, String>> errors = new HashMap<>();
		Map<String, String> errorsBody = new HashMap<>();

		ex.getBindingResult().getAllErrors().forEach((error) -> {

			String fieldName = ((FieldError) error).getField();

			String errorMessage = error.getDefaultMessage();

			errorsBody.put(fieldName, errorMessage);
		});
		errors.put("fieldErrors", errorsBody);
		return errors;
	}

}
