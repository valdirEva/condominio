package com.lda.comdominio.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Repository;

import com.lda.comdominio.model.Veiculo;


@Repository
public interface VeiculoRepository extends JpaRepository<Veiculo, Long>{
	@PreAuthorize("isAuthenticated()")
	Veiculo deleteByPlaca(String veiculoPlaca);
	@PreAuthorize("isAuthenticated()")
	Veiculo findByPlaca(String veiculoPlaca);
	@PreAuthorize("isAuthenticated()")
	List <Veiculo> findByMarcaOrModelo(String marca, String modelo);
	
	

}
