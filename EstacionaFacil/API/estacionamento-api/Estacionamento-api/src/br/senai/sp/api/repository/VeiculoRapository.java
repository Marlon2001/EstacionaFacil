package br.senai.sp.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.senai.sp.api.model.Mensalista;
import br.senai.sp.api.model.Veiculo;

public interface VeiculoRapository extends JpaRepository<Veiculo, Long> {

	@Query("SELECT v From Veiculo v WHERE v.codMensalista = ?1")
	List<Veiculo> getVeiculosByCodMensalista(Mensalista m);

	@Query("SELECT count(v) From Veiculo v WHERE v.placa = ?1")
	int getVeiculosByMensalista(String placa);
	
	

}
