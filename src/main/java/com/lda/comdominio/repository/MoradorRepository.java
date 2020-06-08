package com.lda.comdominio.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Repository;

import com.lda.comdominio.model.Morador;


//@Repository= componente do Spring, interface que extende a JpaRepository 
//e herda todas as implemantaçoes de JPA facilitando a interaçao com o BD.
@Repository
@PreAuthorize("isAuthenticated()")
public interface MoradorRepository extends JpaRepository<Morador, Long> {
	// faz a busca no BD por nome.
	@PreAuthorize("isAuthenticated()")
	List<Morador> findBynome(String nome);

	@PreAuthorize("isAuthenticated()")
	List<Morador> findBynumeroApartamento(long numeroApartamento);

	@PreAuthorize("isAuthenticated()")
	Morador findByrg(String rg);
	@PreAuthorize("isAuthenticated()")
	Morador deleteByrg(String rg);
		
		
		
}
