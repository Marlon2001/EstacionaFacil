package br.senai.sp.api.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import br.senai.sp.api.model.EnderecoMensalista;
import br.senai.sp.api.model.Mensalista;

public interface EnderecoMensalistaRepository extends JpaRepository<EnderecoMensalista, Long> {

	@Query("SELECT e FROM EnderecoMensalista e WHERE e.codEnderecoMensalista = ?1")
	EnderecoMensalista findByCod(Long codEnderecoMensalista);

	@Query("SELECT em From EnderecoMensalista em WHERE em.codMensalista = ?1")
	List<EnderecoMensalista> findByCodMensalista(Mensalista m);

	@Transactional
	@Modifying
	@Query("DELETE FROM EnderecoMensalista em WHERE em.codEndereco.codEndereco = ?1")
	void deleteByCodEndereco(Long codEndereco);
}
