package com.lda.comdominio.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lda.comdominio.model.Morador;


//@Repository= componente do Spring, interface que extende a JpaRepository 
//e herda todas as implemantaçoes de JPA facilitando a interaçao com o BD.
@Repository
public interface MoradorRepository extends JpaRepository <Morador, Long>{
	//faz a busca no BD por nome.
		List<Morador> findBynome(String nome);
		List<Morador> findBynumeroApartamento(long numeroApartamento);
		Morador findByrg(String rg);

		Morador deleteByrg(String rg);
		
		
		
}
