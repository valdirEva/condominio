package com.lda.comdominio.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lda.comdominio.exceptionhandler.EntidadeNaoEncontradaException;
import com.lda.comdominio.exceptionhandler.NegocioException;
import com.lda.comdominio.model.Morador;
import com.lda.comdominio.repository.MoradorRepository;

@Service // componente do spring para colocar nossos serviços.
public class MoradorSevice {
	@Autowired // injeta a interface moradorRepository.
	private MoradorRepository moradorRepository;

	// Lista todos moradores
	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
	public List<Morador> Listar() {
		return moradorRepository.findAll();
	}

	// listar moradores por numero do apartamento
	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
	public List<Morador> listarAp(Long numeroApartamento) {
		List <Morador> moradores = moradorRepository.findBynumeroApartamento(numeroApartamento);
		
		
		return moradores;
	}
	
	// Buscar moradores por  rg
	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
		public Morador buscaRG(String rg) {
			if (moradorRepository.findByrg(rg) == null) {
				throw new EntidadeNaoEncontradaException("RG não encontrado");
			}
			
			return moradorRepository.findByrg(rg);
		}

	// adiciona morador
	@Transactional
	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
	public Morador salvarMorador(String nome, String rg, Date dataNascimento, Long numeroApartamento) {
		Morador morador = new Morador();
			if (moradorRepository.findByrg(rg)!= null) {
			
			throw new NegocioException("Já existe um morador cadastrado com este rg.");
		}
			
		morador.setNome(nome);
		morador.setRg(rg);
		morador.setDataNascimento(dataNascimento);
		morador.setNumeroApartamento(numeroApartamento);
		moradorRepository.save(morador);
		return morador;
	}

	// edita morador
	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
	public Morador atualizaMorador(Morador morador) {
		return moradorRepository.save(morador);
	}

	// deletar morador da tabela
	@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
	public void excluir(long moradorId) {
		moradorRepository.deleteById(moradorId);

	}

}
