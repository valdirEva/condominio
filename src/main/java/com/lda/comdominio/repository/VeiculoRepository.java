package com.lda.comdominio.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lda.comdominio.model.Veiculo;


@Repository
public interface VeiculoRepository extends JpaRepository<Veiculo, Long>{
	Veiculo deleteByPlaca(String veiculoPlaca);
	Veiculo findByPlaca(String veiculoPlaca);
	
	

}
