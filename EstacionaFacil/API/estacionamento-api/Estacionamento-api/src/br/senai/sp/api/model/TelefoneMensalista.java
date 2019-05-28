package br.senai.sp.api.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "tbl_telefone_mensalista")
public class TelefoneMensalista {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="cod_telefone_mensalista")
	private Long codTelefoneMensalista;
	@ManyToOne
	@JoinColumn(name = "cod_mensalista")
	private Mensalista codMensalista;
	@ManyToOne
	@JoinColumn(name = "cod_telefone")
	private Telefone codTelefone;
	@Column(name = "tipo_telefone")
	private String tipoTelefone;

	public Mensalista getCodMensalista() {
		return codMensalista;
	}

	public void setCodMensalista(Mensalista codMensalista) {
		this.codMensalista = codMensalista;
	}

	public Telefone getCodTelefone() {
		return codTelefone;
	}

	public void setCodTelefone(Telefone codTelefone) {
		this.codTelefone = codTelefone;
	}

	public String getTipoTelefone() {
		return tipoTelefone;
	}

	public void setTipoTelefone(String tipoTelefone) {
		this.tipoTelefone = tipoTelefone;
	}

	@Override
	public String toString() {
		return "TelefoneMensalista [codMensalista=" + codMensalista + ", codTelefone=" + codTelefone + ", tipoTelefone="
				+ tipoTelefone + "]";
	}

}
