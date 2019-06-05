package br.senai.sp.api.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tbl_preco")
public class Precos {
// tem que indentificar como estao os camos no banco para o spring saber onde salvar os atriutos
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "cod_preco")
	private Long codPreco;
	@Column(name = "valor_hora_1")
	private Double valorHora1;
	@Column(name = "valor_demais_horas")
	private Double valorDemaisHoras;
	
	@Column(name = "valor_vaga")
	private Double valorVaga;
	
	@Column(name = "valor_diaria")
	private Double valorDiaria;
	
	@Column(name = "data_saida")
	private String dataSaida;

	public Double getValorDiaria() {
		return valorDiaria;
	}

	public void setValorDiaria(Double valorDiaria) {
		this.valorDiaria = valorDiaria;
	}

	public void setValorVaga(Double valorVaga) {
		this.valorVaga = valorVaga;
	}

	public Double getValorVaga() {
		return valorVaga;
	}

	public Long getCodPreco() {
		return codPreco;
	}

	public void setCodPreco(Long codPreco) {
		this.codPreco = codPreco;
	}

	public Double getValorHora1() {
		return valorHora1;
	}

	public void setValorHora1(Double valorHora1) {
		this.valorHora1 = valorHora1;
	}

	public Double getValorDemaisHoras() {
		return valorDemaisHoras;
	}

	public void setValorDemaisHoras(Double valorDemaisHoras) {
		this.valorDemaisHoras = valorDemaisHoras;
	}

	public String getDataSaida() {
		return dataSaida;
	}

	public void setDataSaida(String dataSaida) {
		this.dataSaida = dataSaida;
	}

	@Override
	public String toString() {
		return "Precos [codPreco=" + codPreco + ", valorHora1=" + valorHora1 + ", valorDemaisHoras=" + valorDemaisHoras
				+ ", dataSaida=" + dataSaida + "]";
	}

}
