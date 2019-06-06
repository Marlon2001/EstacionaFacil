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
@Table(name = "tbl_cidade")
public class Cidade {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "cod_cidade")
	private Long codCidade;
	
	@Column(name = "nome_cidade")
	private String nomeCidade;
	
	@ManyToOne
	@JoinColumn(name = "cod_estado")
	private Estado codEstado;

	public Long getCodCidade() {
		return codCidade;
	}

	public void setCodCidade(Long codCidade) {
		this.codCidade = codCidade;
	}

	public String getNomeCidade() {
		return nomeCidade;
	}

	public void setNomeCidade(String nomeCidade) {
		this.nomeCidade = nomeCidade;
	}

	public Estado getCodEstado() {
		return codEstado;
	}

	public void setCodEstado(Estado codEstado) {
		this.codEstado = codEstado;
	}

	@Override
	public String toString() {
		return "Cidade [codCidade=" + codCidade + ", nomeCidade=" + nomeCidade + ", codEstado=" + codEstado + "]";
	}

}
