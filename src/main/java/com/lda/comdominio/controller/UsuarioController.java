package com.lda.comdominio.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
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

	@PostMapping(value = "/novo")
	public Usuario cadastrarUsuario(@RequestBody @Valid UsuarioDTO usuario) {
		return usuarioService.novoUsuario(usuario.getNome(), usuario.getEmail(), usuario.getSenha(),
				usuario.getAutorizacao());
	}
}
