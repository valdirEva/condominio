package com.lda.comdominio.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;
import com.lda.comdominio.model.Veiculo;
import com.lda.comdominio.model.VeiculoDTO;
import com.lda.comdominio.repository.VeiculoRepository;
import com.lda.comdominio.service.VeiculoService;

@RestController // anotacao para indicar ao spring que a classe é controller.
@RequestMapping("/veiculos") // indica onde estao todas requisições.
public class VeiculoController {
	// injetando veiculoService no controller
	@Autowired
	private VeiculoService veiculoService;

	@Autowired // injeta a interface veiculoRepository.
	private VeiculoRepository veiculoRepository;

	// método para listar todos veiculos
	@GetMapping
	@JsonView(View.MoradorResumo.class)
	public List<Veiculo> Listar() {
		return veiculoService.Listar();
	}
	
	// Lista veiculos por placa
	@GetMapping("/{veiculoPlaca}")
	@JsonView(View.MoradorResumo.class)
	public Veiculo BuscaPlaca(@PathVariable String veiculoPlaca) {
		return veiculoService.BuscaPlaca(veiculoPlaca);
	}

	// método para adicionar dados noBD tabela veiculo.
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	@JsonView(View.MoradorResumo.class)
	public Veiculo adicionar(@RequestBody VeiculoDTO veiculo) {
		return veiculoService.salvarVeiculo(veiculo.getRgMorador(), veiculo.getMarca(), veiculo.getModelo(),
				veiculo.getPlaca());
	}

	
	// metodo para deletar dados de veiculo
	@DeleteMapping("/{veiculoId}")
	public ResponseEntity<Void> removeVeiculo(@PathVariable Long veiculoId) {
		if (!veiculoRepository.existsById(veiculoId)) {
			return ResponseEntity.notFound().build();
		}
		veiculoRepository.deleteById(veiculoId);
		return ResponseEntity.noContent().build();

	}

}