package br.senai.sp.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.senai.sp.api.model.EnderecoMensalista;
import br.senai.sp.api.model.Mensalista;
import br.senai.sp.api.model.Telefone;
import br.senai.sp.api.model.TelefoneMensalista;

public interface TelefoneMensalistaRepository extends JpaRepository<TelefoneMensalista, Long> {

	@Query("SELECT tm From TelefoneMensalista tm WHERE tm.codMensalista = ?1")
	List<TelefoneMensalista> findByCodMensalista(Mensalista m);
	
	
	@Query("DELETE FROM TelefoneMensalista tm WHERE tm.codTelefone.codTelefone = ?1")
	void deleteByCodTel(Long t);


	
	

}
