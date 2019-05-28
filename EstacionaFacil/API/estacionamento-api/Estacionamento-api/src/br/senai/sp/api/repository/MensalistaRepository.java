package br.senai.sp.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.senai.sp.api.model.Mensalista;

public interface MensalistaRepository extends JpaRepository <Mensalista, Long> {
	
	@Query("SELECT m FROM Mensalista m WHERE m.codMensalista = ?1")
	Mensalista findByCod(Long codMensalista);
	

}
