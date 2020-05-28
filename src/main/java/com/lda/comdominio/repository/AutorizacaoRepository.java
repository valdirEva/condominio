package com.lda.comdominio.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lda.comdominio.model.Autorizacao;

public interface AutorizacaoRepository extends JpaRepository<Autorizacao, Long> {
	public Autorizacao findByNome(String nome);
}
