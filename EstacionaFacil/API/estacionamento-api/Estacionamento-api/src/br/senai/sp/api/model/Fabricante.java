package br.senai.sp.api.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tbl_fabricante")
public class Fabricante {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column (name = "cod_fabricante")
	private Long codFabricante;
	@Column(name = "fabricante")
	private String fabricante;

	public Long getCodFabricante() {
		return codFabricante;
	}

	public void setCodFabricante(Long codFabricante) {
		this.codFabricante = codFabricante;
	}

	public String getFabrivante() {
		return fabricante;
	}

	public void setFabrivante(String fabrivante) {
		this.fabricante = fabrivante;
	}

}
