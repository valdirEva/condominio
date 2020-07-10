package com.lda.comdominio.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
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
import com.lda.comdominio.service.VeiculoService;

@RestController // anotacao para indicar ao spring que a classe é controller.
@CrossOrigin
@RequestMapping("/veiculos") // indica onde estao todas requisições.
public class VeiculoController {
	// injetando veiculoService no controller
	@Autowired
	private VeiculoService veiculoService;

	

	// método para listar todos veiculos
	@GetMapping
	@JsonView(View.MoradorCompleto.class)
	public List<Veiculo> Listar() {
		return veiculoService.Listar();
	}
	
	// método para listar todos veiculos por marca ou modelo
	@GetMapping("mm/{marcaOuModelo}")
	@JsonView(View.MoradorCompleto.class)
	public List<Veiculo> ListarPorMarcaOuModelo(@PathVariable String marcaOuModelo) {
		return veiculoService.ListarPorMarcaOuModelo(marcaOuModelo,marcaOuModelo);
	}
	
	// Lista veiculos por placa
	@GetMapping("/{veiculoPlaca}")
	@JsonView(View.MoradorCompleto.class)
	public Veiculo BuscaPlaca(@PathVariable String veiculoPlaca) {
		return veiculoService.BuscaPlaca(veiculoPlaca);
	}

	// método para adicionar dados noBD tabela veiculo.
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	@JsonView(View.MoradorCompleto.class)
	public Veiculo adicionar(@RequestBody @Valid VeiculoDTO veiculo) {
		return veiculoService.salvarVeiculo(veiculo.getRgMorador(), veiculo.getMarca(), veiculo.getModelo(),
				veiculo.getPlaca());
	}

	
	// metodo para deletar dados de veiculo
	@DeleteMapping("/{veiculoId}")
	public ResponseEntity<Void> removeVeiculo(@PathVariable Long veiculoId) {
		veiculoService.excluir(veiculoId);
		return ResponseEntity.noContent().build();

	}

}
