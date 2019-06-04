package br.senai.sp.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.senai.sp.api.model.Telefone;

public interface TelefoneRepository extends JpaRepository <Telefone, Long> {
	
	@Query("SELECT t FROM Telefone t WHERE t.codTelefone = ?1")
	Telefone findByCod(Long codTelefone);

}
