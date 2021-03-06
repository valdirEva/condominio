package com.lda.comdominio.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lda.comdominio.exceptionhandler.EntidadeNaoEncontradaException;
import com.lda.comdominio.exceptionhandler.NegocioException;
import com.lda.comdominio.model.Morador;
import com.lda.comdominio.model.Veiculo;
import com.lda.comdominio.repository.MoradorRepository;
import com.lda.comdominio.repository.VeiculoRepository;

@Service // componente do spring para colocar nossos serviços.
public class VeiculoService {

	@Autowired // injeta a interface VeiculoRepository.
	private VeiculoRepository veiculoRepository;

	@Autowired // injeta a interface moradorRepository.
	private MoradorRepository moradorRepository;

	// Lista todos veiculos
	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
	public List<Veiculo> Listar() {
		return veiculoRepository.findAll();
	}
	
	// Lista todos veiculos por placa
	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
	public Veiculo BuscaPlaca(String veiculoPlaca) {
		if (veiculoRepository.findByPlaca(veiculoPlaca) == null) {
			throw new EntidadeNaoEncontradaException("Placa não encontrada");
		}
		return veiculoRepository.findByPlaca(veiculoPlaca);
	}
	//lista veiculos por marca ou modelo
	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
	public List<Veiculo> ListarPorMarcaOuModelo(String marca,String modelo) {
		if (veiculoRepository.findByMarcaOrModelo(marca, modelo) == null) {
			throw new EntidadeNaoEncontradaException("Placa não encontrada");
		}
		return veiculoRepository.findByMarcaOrModelo(marca, modelo);
	}
	
	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
	public List<Veiculo> BuscaVeiculoNomeOuRg(String nome,String rg) {
		if (veiculoRepository.findByMoradorNomeOrMoradorRg(nome, rg) == null) {
			throw new EntidadeNaoEncontradaException("Não encontrado");
		}
		return veiculoRepository.findByMoradorNomeOrMoradorRg(nome, rg);
	}


	// adiciona veiculo
	
	@Transactional
	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
	public Veiculo salvarVeiculo(String rgMorador, String marca, String modelo, String placa) {
		Morador morador = moradorRepository.findByrg(rgMorador);

		if (morador == null) {
			throw new EntidadeNaoEncontradaException("RG não encontrado");
		}
		if (veiculoRepository.findByPlaca(placa) != null) {
			throw new NegocioException("Placa ja cadastrada anteriormente.");
		}

		Veiculo veiculo = new Veiculo();
		veiculo.setMorador(morador);
		veiculo.setMarca(marca);
		veiculo.setModelo(modelo);
		veiculo.setPlaca(placa);
		veiculoRepository.save(veiculo);
		return veiculo;
	}

	

	// deletar veiculo da tabela
	@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
	public void excluir(long veiculoId) {
		
		veiculoRepository.deleteById(veiculoId);

	}

}
