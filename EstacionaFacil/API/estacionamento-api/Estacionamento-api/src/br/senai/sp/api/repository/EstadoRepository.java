package br.senai.sp.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.senai.sp.api.model.Estado;

public interface EstadoRepository extends JpaRepository< Estado, Long> {
	
	@Query("SELECT e FROM Estado e WHERE e.codEstado = ?1")
	Estado findByCod(Long codEstado);
	

}
