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
@Table(name = "tbl_veiculo_mensalista")
public class VeiculoMensalista {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "cod_veiculo_mensalista")
	private Long codVeiculoMensalista;
	@ManyToOne
	@JoinColumn(name = "cod_mensalista")
	private Mensalista codMensalista;
	@ManyToOne
	@JoinColumn(name = "cod_veiculo")
	private Veiculo codVeiculo;

	public Mensalista getCodMensalista() {
		return codMensalista;
	}

	public void setCodMensalista(Mensalista codMensalista) {
		this.codMensalista = codMensalista;
	}

	public Veiculo getCodVeiculo() {
		return codVeiculo;
	}

	public void setCodVeiculo(Veiculo codVeiculo) {
		this.codVeiculo = codVeiculo;
	}

	public Long getCodVeiculoMensalista() {
		return codVeiculoMensalista;
	}

	public void setCodVeiculoMensalista(Long codVeiculoMensalista) {
		this.codVeiculoMensalista = codVeiculoMensalista;
	}

	@Override
	public String toString() {
		return "VeiculoMensalista [codVeiculoMensalista=" + codVeiculoMensalista + ", codMensalista=" + codMensalista
				+ ", codVeiculo=" + codVeiculo + "]";
	}

}
