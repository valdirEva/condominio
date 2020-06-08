package com.lda.comdominio.service;

import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
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
	@Autowired
	private PasswordEncoder passEncoder;
	
	
	
	// Lista todos usuarios
		@Override
		@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
		public List<Usuario> Listar() {
			return usuarioRepo.findAll();
		}
	
	
	/*
	 * Sobreescrevendo método para buscar no repositorio usuario por nome ou email.
	 * Caso não encontre retorna usuario não encontrado.
	 */

	@Override 
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Usuario usuario = usuarioRepo.findTop1ByNomeOrEmail(username, username);
		if (usuario == null) {
			throw new UsernameNotFoundException("Usuário " + username + " não encontrado");
		}
		return User.builder().username(username).password(usuario.getSenha())
				.authorities(usuario.getAutorizacoes().stream().map(Autorizacao::getNome).collect(Collectors.toList())
						.toArray(new String[usuario.getAutorizacoes().size()]))
				.build();
	}
	
	
	
	
	
	@Override
	@Transactional
	@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
	public Usuario novoUsuario(String nome, String email, String senha, String nomeAutorizacao) {
		if (usuarioRepo.findTop1ByNomeOrEmail(nome,email)!= null) {
			throw new NegocioException("Já existe um usuario cadastrado com este nome ou email.");
		}
		Autorizacao autorizacao = autorizacaoRepo.findByNome(nomeAutorizacao);
		if (autorizacao == null) {
			autorizacao = new Autorizacao();
			autorizacao.setNome(nomeAutorizacao);
			autorizacaoRepo.save(autorizacao);
		}
		
		Usuario usuario = new Usuario();
		usuario.setNome(nome);
		usuario.setEmail(email);
		usuario.setSenha(passEncoder.encode(senha));
		usuario.setAutorizacoes(new HashSet<Autorizacao>());
		usuario.getAutorizacoes().add(autorizacao);
		usuarioRepo.save(usuario);
		return usuario;
	}
	
	
	
	
	
	
	
	
	// deleta usuario
	@Override
	@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
	public Usuario deleteUsuario(Long usuarioId) {
		
		usuarioRepo.deleteById(usuarioId);
		return null;
	}
}
