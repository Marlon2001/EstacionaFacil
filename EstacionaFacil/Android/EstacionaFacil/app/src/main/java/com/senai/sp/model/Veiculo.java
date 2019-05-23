package com.senai.sp.model;

public class Veiculo {


    private Long codVeiculo;
    private String anoVeiculo;
    private String modelo;
    private String placa;
    private Fabricante codFabricante;
    private Mensalista codMensalista;


    public Long getCodVeiculo() {
        return codVeiculo;
    }

    public void setCodVeiculo(Long codVeiculo) {
        this.codVeiculo = codVeiculo;
    }

    public String getAnoVeiculo() {
        return anoVeiculo;
    }

    public void setAnoVeiculo(String anoVeiculo) {
        this.anoVeiculo = anoVeiculo;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public Fabricante getCodFabricante() {
        return codFabricante;
    }

    public void setCodFabricante(Fabricante codFabricante) {
        this.codFabricante = codFabricante;
    }

    public Mensalista getCodMensalista() {
        return codMensalista;
    }

    public void setCodMensalista(Mensalista codMensalista) {
        this.codMensalista = codMensalista;
    }

    @Override
    public String toString() {
        return "Veiculo{" +
                "codVeiculo=" + codVeiculo +
                ", anoVeiculo='" + anoVeiculo + '\'' +
                ", modelo='" + modelo + '\'' +
                ", placa='" + placa + '\'' +
                ", codFabricante=" + codFabricante +
                ", codMensalista=" + codMensalista +
                '}';
    }
}
