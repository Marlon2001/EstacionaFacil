package br.senai.sp.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.senai.sp.api.model.Movimentacao;

public interface EstacionamentoRepository extends JpaRepository<Movimentacao, Long> {
	
	@Query("SELECT m FROM Movimentacao m WHERE m.dataSaida IS NULL")
	List<Movimentacao> findByHoraSaidaIsNull();

	@Query("SELECT m FROM Movimentacao m WHERE m.placa = ?1")
	Movimentacao findByPlaca(String placa);
	
}
