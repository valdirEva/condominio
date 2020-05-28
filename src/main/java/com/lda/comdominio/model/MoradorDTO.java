package com.lda.comdominio.model;

import java.util.Date;

public class MoradorDTO {
	private String nome;
	private String rg;
	private Date dataNascimento;
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
