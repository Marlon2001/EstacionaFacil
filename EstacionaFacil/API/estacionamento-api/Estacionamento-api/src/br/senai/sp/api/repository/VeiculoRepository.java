package br.senai.sp.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.senai.sp.api.model.Veiculo;

public interface VeiculoRepository extends JpaRepository<Veiculo, Long> {
	
	//select count(veiculo.cod_veiculo) from tbl_veiculo as veiculo inner join 
	//tbl_mensalista as mensalista on mensalista.cod_mensalista = veiculo.cod_mensalista 
	//where veiculo.placa = "ASD-5854";
	
	@Query("SELECT count(v) FROM Veiculo v INNER JOIN Mensalista m ON m.codMensalista = v.codMensalista WHERE v.placa = ?1 ")
	int findyByPlaca(String placa);
		
	

}
