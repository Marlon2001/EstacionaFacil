package br.senai.sp.api.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tbl_estado")
public class Estado {
	@Id
	@Column(name = "cod_estado")
	private Long codEstado;
	@Column(name = "nome_estado")
	private String nomeEstado;
	@Column(name = "sigla")
	private String sigla;

	public Long getCodEstado() {
		return codEstado;
	}

	public void setCodEstado(Long codEstado) {
		this.codEstado = codEstado;
	}

	public String getNomeEstado() {
		return nomeEstado;
	}

	public void setNomeEstado(String nomeEstado) {
		this.nomeEstado = nomeEstado;
	}

	public String getSigla() {
		return sigla;
	}

	public void setSigla(String sigla) {
		this.sigla = sigla;
	}

	@Override
	public String toString() {
		return "Estado [codEstado=" + codEstado + ", nomeEstado=" + nomeEstado + ", sigla=" + sigla + "]";
	}

}
