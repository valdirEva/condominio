package com.lda.comdominio.service;

import java.util.List;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;



import com.lda.comdominio.model.Morador;
import com.lda.comdominio.repository.MoradorRepository;

@Service // componente do spring para colocar nossos serviços.
public class MoradorSevice {
	@Autowired // injeta a interface moradorRepository.
	private MoradorRepository moradorRepository;

	public List<Morador> Listar() {
		return moradorRepository.findAll();
	}
	//listar moradores por numero do apartamento
	public List<Morador> listarAp( Long numeroApartamento) {
		return moradorRepository.findBynumeroApartamento(numeroApartamento);
	}

	// cadastrar novo morador
	public Morador salvarMorador(Morador morador) {

		return moradorRepository.save(morador);// salva ou edita morador.
	}

	// atualizar morador
	public ResponseEntity<Morador> atualizar( Long moradorId,  Morador morador) {
		if (!moradorRepository.existsById(moradorId)) {
			return ResponseEntity.notFound().build();
		}
		morador.setId(moradorId);
		morador = salvarMorador(morador);

		return ResponseEntity.ok(morador);
	}

	// deletar morador da tabela
	public ResponseEntity<Void> excluir( Long moradorId) {
		if (!moradorRepository.existsById(moradorId)) {
			return ResponseEntity.notFound().build();
		}
		moradorRepository.deleteById(moradorId);
		return ResponseEntity.noContent().build();
	}

}
