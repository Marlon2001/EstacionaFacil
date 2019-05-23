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
@Table(name = "tbl_endereco")
public class Endereco {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "cod_endereco")
	private Long codEndereco;
	@Column(name = "logradouro")
	private String logradouro;
	@Column(name = "numero")
	private String numero;
	@Column(name = "bairro")
	private String bairro;
	
	@ManyToOne
	@JoinColumn(name = "codCidade")
	private Cidade codCidade;
	
	
	
	
	public Long getCodEndereco() {
		return codEndereco;
	}




	public void setCodEndereco(Long codEndereco) {
		this.codEndereco = codEndereco;
	}




	public String getLogradouro() {
		return logradouro;
	}




	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}




	public String getNumero() {
		return numero;
	}




	public void setNumero(String numero) {
		this.numero = numero;
	}




	public String getBairro() {
		return bairro;
	}




	public void setBairro(String bairro) {
		this.bairro = bairro;
	}




	public Cidade getCodCidade() {
		return codCidade;
	}




	public void setCodCidade(Cidade codCidade) {
		this.codCidade = codCidade;
	}




	@Override
	public String toString() {
		return "Endereco [codEndereco=" + codEndereco + ", logradouro=" + logradouro + ", numero=" + numero
				+ ", bairro=" + bairro + ", codCidade=" + codCidade + "]";
	}
	
	
	

}
