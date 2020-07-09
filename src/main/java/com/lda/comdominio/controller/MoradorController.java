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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;
import com.lda.comdominio.model.Morador;
import com.lda.comdominio.model.MoradorDTO;
import com.lda.comdominio.service.MoradorSevice;

@RestController // anotacao para indicar ao spring que a classe é controller.
@CrossOrigin
@RequestMapping("/moradores") // indica onde estao todas requisições.
public class MoradorController {

	// injetando MoradorService no controller
	@Autowired
	private MoradorSevice moradorService;

	
	// método para listar todos moradores
	@GetMapping (value = "/todos")
	@JsonView(View.MoradorResumo.class)
	public List<Morador> Listar() {
		return moradorService.Listar();
	}

	// método para listar todos moradores de um apartamento.
	@GetMapping("/{numeroApartamento}")
	@JsonView(View.MoradorResumo.class)
	public List<Morador> listarAp(@PathVariable Long numeroApartamento) {
		return moradorService.listarAp(numeroApartamento);
	}
	
	// Buscar moradores por  rg
	@GetMapping("buscarg/{rg}")
	@JsonView(View.MoradorResumo.class)
	public Morador buscaRG(@PathVariable String rg) {
			return moradorService.buscaRG(rg);
			}

	// método para adicionar dados noBD tabela morador.
	@PostMapping(value = "/novo")
	@ResponseStatus(HttpStatus.CREATED)
	public Morador adicionar(@RequestBody @Valid MoradorDTO morador) {
		return moradorService.salvarMorador(morador.getNome(),
				morador.getRg(),
				morador.getDataNascimento(),
				morador.getNumeroApartamento());
	}

	// metodo para atualizar dados de morador
	@JsonView(View.MoradorCompleto.class)
	@PutMapping("atualizar/{moradorId}")
	public ResponseEntity<Morador> atualizar(@PathVariable Long moradorId, @RequestBody Morador morador) {
		moradorService.atualizaMorador(morador);
		return ResponseEntity.ok(morador);
	}

	// metodo para deletar  morador
	@DeleteMapping("delete/{id}")
	public ResponseEntity<Void> removeMorador(@PathVariable Long id) {
		
		moradorService.excluir(id);
		return ResponseEntity.noContent().build();

	}
}
