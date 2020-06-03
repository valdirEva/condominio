package com.lda.comdominio.service;

import java.util.Date;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
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
		List <Morador> moradores = moradorRepository.findBynumeroApartamento(numeroApartamento);
		if (moradores.size() == 0) {
			throw new UsernameNotFoundException(
					"Apartamento sem moradores cadastrados " );
		}
		
		return moradores;
	}
	
	// Buscar moradores por  rg
		public Morador buscaRG(String rg) {
			if (moradorRepository.findByrg(rg) == null) {
				throw new UsernameNotFoundException(
						"Morador com rg: "+
								rg+
						", não esta cadastrado.");
			}
			
			return moradorRepository.findByrg(rg);
		}

	// adiciona morador
	@Transactional
	public Morador salvarMorador(String nome, String rg, Date dataNascimento, Long numeroApartamento) {
		Morador morador = new Morador();
		if (moradorRepository.findByrg(rg)!= null) {
			Morador morador1= moradorRepository.findByrg(rg);
			throw new UsernameNotFoundException(
					"Morador com rg: " +
					morador1.getRg() + 
					" ja é cadastrado  " );
		}
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
