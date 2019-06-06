package br.senai.sp.api.model;

import javax.persistence.Column;
import javax.persistence.Entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import javax.validation.constraints.NotNull;

@Entity
@Table(name = "tbl_movimentacao")
public class Movimentacao {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long codMovimentacao;
	
	@NotNull
	@Column(name = "placa")
	private String placa;
	
	@Column(name = "modelo")
	private String modelo;
	
	@Column(name = "data_entrada")
	private String dataEntrada;
	
	@Column(name = "data_saida")
	private String dataSaida;
	
	@Column(name = "tempo_permanencia")
	private Integer tempoPermanencia;
	
	@Column(name = "valor_pago")
	private Double valorPago;
	private String tipo;

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public Long getCodMovimentacao() {
		return codMovimentacao;
	}

	public void setCodMovimentacao(Long codMovimentacao) {
		this.codMovimentacao = codMovimentacao;
	}

	public String getDataEntrada() {
		return dataEntrada;
	}

	public void setDataEntrada(String dataEntrada) {
		this.dataEntrada = dataEntrada;
	}

	public String getDataSaida() {
		return dataSaida;
	}

	public void setDataSaida(String dataSaida) {
		this.dataSaida = dataSaida;
	}

	public Integer getTempoPermanencia() {
		return tempoPermanencia;
	}

	public void setTempoPermanencia(Integer tempoPermanencia) {
		this.tempoPermanencia = tempoPermanencia;
	}

	public Double getValorPago() {
		return valorPago;
	}

	public void setValorPago(Double valorPago) {
		this.valorPago = valorPago;
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

	@Override
	public String toString() {
		return "Movimentacao [codMovimentacao=" + codMovimentacao + ", placa=" + placa + ", modelo=" + modelo
				+ ", dataEntrada=" + dataEntrada + ", dataSaida=" + dataSaida + ", tempoPermanencia=" + tempoPermanencia
				+ ", valorPago=" + valorPago + "]";
	}
}
