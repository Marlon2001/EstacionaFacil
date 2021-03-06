package com.senai.sp.model;

import java.io.Serializable;

public class Veiculo implements Serializable{

    private int codVeiculo;
    private String anoVeiculo;
    private String modelo;
    private String placa;
    private int codFabricante;

    public void setCodFabricante(int codFabricante) {
        this.codFabricante = codFabricante;
    }

    public int getCodFabricante() {
        return codFabricante;
    }

    public int getCodVeiculo() {
        return codVeiculo;
    }

    public void setCodVeiculo(int codVeiculo) {
        this.codVeiculo = codVeiculo;
    }

    public String getAnoVeiculo() {
        return anoVeiculo.substring(0,4);
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
}
