package com.lda.comdominio.service;

import java.util.Date;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import com.lda.comdominio.model.Morador;

import com.lda.comdominio.repository.MoradorRepository;

@Service // componente do spring para colocar nossos serviços.
public class MoradorSevice {
	@Autowired // injeta a interface moradorRepository.
	private MoradorRepository moradorRepository;

	// Lista todos moradores
	public List<Morador> Listar() {
		return moradorRepository.findAll();
	}

	// listar moradores por numero do apartamento
	public List<Morador> listarAp(Long numeroApartamento) {
		return moradorRepository.findBynumeroApartamento(numeroApartamento);
	}

	// adiciona morador
	@Transactional
	public Morador salvarMorador(String nome, String rg, Date dataNascimento, Long numeroApartamento) {
		Morador morador = new Morador();
		morador.setNome(nome);
		morador.setRg(rg);
		morador.setDataNascimento(dataNascimento);
		morador.setNumeroApartamento(numeroApartamento);
		moradorRepository.save(morador);
		return morador;
	}
	
	
   // edita morador
	public Morador atualizaMorador(Morador morador) {
		return moradorRepository.save(morador);
	}

	// deletar morador da tabela
	public void excluir(long moradorId) {
		moradorRepository.deleteById(moradorId);
	}

}
