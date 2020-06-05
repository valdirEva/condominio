package com.lda.comdominio.model;

import java.util.Date;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


public class MoradorDTO {
	@NotBlank (message = "{name.not.blank}")
	private String nome;
	
	@NotBlank (message = "{rg.not.blank}")
	private String rg;
	
	@NotNull (message = "{dataNascimento.not.null}")
	private Date dataNascimento;
	
	@NotNull (message = "{numeroApartamento.not.null}")
	private Long numeroApartamento;
	
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getRg() {
		return rg;
	}
	public void setRg(String rg) {
		this.rg = rg;
	}
	public Date getDataNascimento() {
		return dataNascimento;
	}
	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}
	public Long getNumeroApartamento() {
		return numeroApartamento;
	}
	public void setNumeroApartamento(Long numeroApartamento) {
		this.numeroApartamento = numeroApartamento;
	}
	
	
	

}
