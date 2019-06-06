package br.senai.sp.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.senai.sp.api.model.Cidade;

public interface CidadeRepository extends JpaRepository<Cidade, Long> {

	@Query("SELECT c FROM Cidade c WHERE c.codCidade = ?1")
	Cidade findByCod(Long codCidade);

	@Query("SELECT c FROM Cidade c WHERE c.codEstado.codEstado = ?1")
	List<Cidade> getCidadeByCodEstado(Long cod);
}
