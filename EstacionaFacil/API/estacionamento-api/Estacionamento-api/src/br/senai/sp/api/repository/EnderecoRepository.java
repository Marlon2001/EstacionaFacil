package br.senai.sp.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.senai.sp.api.model.Endereco;


public interface EnderecoRepository  extends JpaRepository<Endereco, Long> {
	
	@Query("SELECT e FROM Endereco e WHERE e.codEndereco = ?1")
	Endereco findByCod(Long codEndereco);
	
	
}
