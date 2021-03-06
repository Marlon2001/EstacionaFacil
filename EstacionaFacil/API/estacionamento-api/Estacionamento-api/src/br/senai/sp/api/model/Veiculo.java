package br.senai.sp.api.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "tbl_veiculo")
public class Veiculo {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "cod_veiculo")
	private Long codVeiculo;
	
	private String placa;
	
	private String modelo;
	
	@Column(name = "ano_veiculo")
	private String anoVeiculo;

	@ManyToOne
	@JoinColumn(name = "cod_fabricante")
	private Fabricante fabricante;

	@ManyToOne
	@JoinColumn(name = "cod_mensalista")
	private Mensalista codMensalista;

	public Mensalista getCodMensalista() {
		return codMensalista;
	}

	public void setCodMensalista(Mensalista codMensalista) {
		this.codMensalista = codMensalista;
	}

	public Long getCodVeiculo() {
		return codVeiculo;
	}

	public void setCodVeiculo(Long codVeiculo) {
		this.codVeiculo = codVeiculo;
	}

	public String getPlaca() {
		return placa;
	}

	public void setPlaca(String placa) {
		this.placa = placa;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public String getAnoVeiculo() {
		return anoVeiculo;
	}

	public void setAnoVeiculo(String anoVeiculo) {
		this.anoVeiculo = anoVeiculo;
	}

	public Fabricante getFabricante() {
		return fabricante;
	}

	public void setFabricante(Fabricante fabricante) {
		this.fabricante = fabricante;
	}

	@Override
	public String toString() {
		return "Veiculo [codVeiculo=" + codVeiculo + ", placa=" + placa + ", modelo=" + modelo + ", anoVeiculo="
				+ anoVeiculo + ", fabricante=" + fabricante + ", codMensalista=" + codMensalista + "]";
	}

}