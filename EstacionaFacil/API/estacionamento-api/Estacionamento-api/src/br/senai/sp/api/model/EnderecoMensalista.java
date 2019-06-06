package br.senai.sp.api.model;

import javax.persistence.CascadeType; 
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "tbl_endereco_mensalista")
public class EnderecoMensalista {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "cod_endereco_mensalista")
	private Long codEnderecoMensalista;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "cod_mensalista")
	private Mensalista codMensalista;

	@OneToOne()
	@JoinColumn(name = "cod_endereco")
	private Endereco codEndereco;

	@Column(name = "descricao")
	private String descricao;

	public Mensalista getCodMensalista() {
		return codMensalista;
	}

	public void setCodMensalista(Mensalista codMensalista) {
		this.codMensalista = codMensalista;
	}

	public Endereco getCodEndereco() {
		return codEndereco;
	}

	public void setCodEndereco(Endereco codEndereco) {
		this.codEndereco = codEndereco;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	@Override
	public String toString() {
		return "EnderecoMensalista [codMensalista=" + codMensalista + ", codEndereco=" + codEndereco + ", descricao="
				+ descricao + "]";
	}

}
