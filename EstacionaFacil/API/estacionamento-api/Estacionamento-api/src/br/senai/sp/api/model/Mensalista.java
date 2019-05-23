package br.senai.sp.api.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tbl_mensalista")
public class Mensalista {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "cod_mensalista")
	private Long codMensalista;
	@Column(name = "nome")
	private String nome;
	@Column(name = "email")
	private String email;
	
	

	public Long getCodMensalista() {
		return codMensalista;
	}

	public void setCodMensalista(Long codMensalista) {
		this.codMensalista = codMensalista;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "Mensalista [codMensalista=" + codMensalista + ", nome=" + nome + ", email=" + email + "]";
	}

}
