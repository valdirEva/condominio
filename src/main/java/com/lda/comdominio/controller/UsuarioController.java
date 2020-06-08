package com.lda.comdominio.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lda.comdominio.model.Usuario;
import com.lda.comdominio.model.UsuarioDTO;
import com.lda.comdominio.service.UsuarioService;

@RestController
@RequestMapping(value = "/usuario")
@CrossOrigin
public class UsuarioController {
	@Autowired
	private UsuarioService usuarioService;
	
	
	// método para listar todos moradores
	@GetMapping
	public List<Usuario> Listar() {
		return usuarioService.Listar();
	}
	
	
//Método para cadastrar usuario
	@PostMapping(value = "/novo")
	public Usuario cadastrarUsuario(@RequestBody @Valid UsuarioDTO usuario) {
		return usuarioService.novoUsuario(usuario.getNome(), usuario.getEmail(), usuario.getSenha(),
				usuario.getAutorizacao());
	}
	
	
	
	
	// metodo para deletar usuario
	@DeleteMapping("/{usuarioId}")
	public ResponseEntity<Void> removeUsuario(@PathVariable Long usuarioId) {
		
		usuarioService.deleteUsuario(usuarioId);
		return ResponseEntity.noContent().build();

	}
}
