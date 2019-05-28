package br.senai.sp.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.senai.sp.api.model.EnderecoMensalista;


public interface EnderecoMensalistaRepository extends JpaRepository<EnderecoMensalista, Long>{
	
	@Query("SELECT e FROM EnderecoMensalista e WHERE e.codEnderecoMensalista = ?1")
	EnderecoMensalista findByCod(Long codEnderecoMensalista);
	

}
