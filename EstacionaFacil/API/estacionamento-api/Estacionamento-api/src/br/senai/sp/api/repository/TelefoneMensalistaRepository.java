package br.senai.sp.api.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import br.senai.sp.api.model.Mensalista;
import br.senai.sp.api.model.TelefoneMensalista;

public interface TelefoneMensalistaRepository extends JpaRepository<TelefoneMensalista, Long> {

	@Query("SELECT tm From TelefoneMensalista tm WHERE tm.codMensalista = ?1")
	List<TelefoneMensalista> findByCodMensalista(Mensalista m);

	@Transactional
	@Modifying
	@Query("DELETE FROM TelefoneMensalista WHERE codTelefone.codTelefone = ?1")
	void deleteByCodTel(Long t);

}
