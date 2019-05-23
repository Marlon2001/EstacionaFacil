package br.senai.sp.api.repository;

import org.springframework.data.jpa.repository.JpaRepository; 
import org.springframework.data.jpa.repository.Query;

import br.senai.sp.api.model.Fabricante;

public interface FabricanteRepository extends JpaRepository<Fabricante, Long> {
	
	@Query("SELECT f FROM Fabricante f WHERE f.codFabricante = ?1")
	Fabricante findByCod(Long codFabricante);
	
}
