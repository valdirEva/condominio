package com.lda.comdominio.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.lda.comdominio.model.Morador;
import com.lda.comdominio.service.MoradorSevice;

@RestController // anotacao para indicar ao spring que a classe é controller.
@RequestMapping("/moradores") // indica onde estao todas requisições.
public class MoradorController {

	// injetando MoradorService no controller
	@Autowired
	private MoradorSevice moradorService;

	// método para listar todos moradores
	@GetMapping
	public List<Morador> Listar() {
		return moradorService.Listar();
	}

	
	// método para listar todos moradores de um apartamento.
	@GetMapping("/{numeroApartamento}")
	public List<Morador> listarAp( @PathVariable Long numeroApartamento) {
		return moradorService.listarAp(numeroApartamento);
	}

	// método para adicionar dados noBD tabela morador.
	@PostMapping 
	@ResponseStatus(HttpStatus.CREATED)
	public Morador adicionar(@RequestBody Morador morador) {
		return moradorService.salvarMorador(morador);
	}
	

	// metodo para atualizar dados de morador	
	@PutMapping("/{moradorId}") 					
	public ResponseEntity<Morador> atualizar(@PathVariable Long moradorId,@RequestBody Morador morador) {
		return moradorService.atualizar(moradorId, morador);

	}

	
	// metodo para deletar dados de morador	
	@DeleteMapping("/{moradorId}") 
	public ResponseEntity<Void> removeMorador(@PathVariable Long moradorId) {
		return moradorService.excluir(moradorId);
	}

}
