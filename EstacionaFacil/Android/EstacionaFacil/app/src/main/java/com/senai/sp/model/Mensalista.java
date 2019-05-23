package com.senai.sp.model;

public class Mensalista {

    private int codVeiculoMensalista;
    private Mensalista codMensalista;
    private Veiculo codVeiculo;

    public int getCodVeiculoMensalista() {
        return codVeiculoMensalista;
    }

    public void setCodVeiculoMensalista(int codVeiculoMensalista) {
        this.codVeiculoMensalista = codVeiculoMensalista;
    }

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

    @Override
    public String toString() {
        return "Mensalista{" +
                "codVeiculoMensalista=" + codVeiculoMensalista +
                ", codMensalista=" + codMensalista +
                ", codVeiculo=" + codVeiculo +
                '}';
    }
}
