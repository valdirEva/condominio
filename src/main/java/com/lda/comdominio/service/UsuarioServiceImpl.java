package com.lda.comdominio.service;

import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lda.comdominio.exceptionhandler.NegocioException;
import com.lda.comdominio.model.Autorizacao;
import com.lda.comdominio.model.Usuario;
import com.lda.comdominio.repository.AutorizacaoRepository;
import com.lda.comdominio.repository.UsuarioRepository;

@Service("usuarioService")
public class UsuarioServiceImpl implements UsuarioService {
	@Autowired
	private AutorizacaoRepository autorizacaoRepo;
	@Autowired
	private UsuarioRepository usuarioRepo;

	@Override
	@Transactional
	public Usuario novoUsuario(String nome, String email, String senha, String nomeAutorizacao) {
		Autorizacao autorizacao = autorizacaoRepo.findByNome(nomeAutorizacao);
		if (autorizacao == null) {
			autorizacao = new Autorizacao();
			autorizacao.setNome(nomeAutorizacao);
			autorizacaoRepo.save(autorizacao);
		}
		else throw new NegocioException("usuario ja possui cadastro");
		Usuario usuario = new Usuario();
		usuario.setNome(nome);
		usuario.setEmail(email);
		usuario.setSenha(senha);
		usuario.setAutorizacoes(new HashSet<Autorizacao>());
		usuario.getAutorizacoes().add(autorizacao);
		usuarioRepo.save(usuario);
		return usuario;
	}
}