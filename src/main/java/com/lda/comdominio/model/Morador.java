package com.lda.comdominio.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "mor_morador")
public class Morador {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "mor_id")
	private Long id;
	
	@Column(name = "mor_nome", length = 20, nullable = false)
	private String nome;
	
	@Column(name = "mor_rg ", unique = true, length = 30, nullable = false)
	private String rg;
	
	@Column(name = "mor_data_nascimento", nullable = false)
	private Date dataNascimento;
	
	@Column(name = "mor_numero_partamento", nullable = false)
	private Long numeroApartamento;
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
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
