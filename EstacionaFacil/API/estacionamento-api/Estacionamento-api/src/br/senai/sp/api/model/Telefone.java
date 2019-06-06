package br.senai.sp.api.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tbl_telefone")
public class Telefone {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "cod_telefone")
	private Long codTelefone;
	
	@Column(name = "telefone")
	private String telefone;

	public Long getCodTelefone() {
		return codTelefone;
	}

	public void setCodTelefone(Long codTelefone) {
		this.codTelefone = codTelefone;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	@Override
	public String toString() {
		return "Telefone [codTelefone=" + codTelefone + ", telefone=" + telefone + "]";
	}

}
