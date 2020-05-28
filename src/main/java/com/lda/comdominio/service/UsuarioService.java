package com.lda.comdominio.service;

import com.lda.comdominio.model.Usuario;

public interface UsuarioService {
	public Usuario novoUsuario(String nome, String email, String senha, String nomeAutorizacao);
}
