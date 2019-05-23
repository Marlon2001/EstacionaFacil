package br.senai.sp.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.senai.sp.api.model.Precos;

public interface PrecosRepository extends JpaRepository<Precos, Long>{
	@Query("SELECT p FROM Precos p where p.dataSaida IS NULL")
	Precos findByDataSaidaIsNull();
		
}
