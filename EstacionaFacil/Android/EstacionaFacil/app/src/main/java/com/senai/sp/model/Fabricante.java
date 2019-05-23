package com.senai.sp.model;

public class Fabricante {
    private Long codFabricante;
    private String fabricante;

    public Long getCodFabricante() {
        return codFabricante;
    }

    public void setCodFabricante(Long codFabricante) {
        this.codFabricante = codFabricante;
    }

    public String getFabricante() {
        return fabricante;
    }

    public void setFabricante(String fabricante) {
        this.fabricante = fabricante;
    }

    @Override
    public String toString() {
        return "Fabricante{" +
                "codFabricante=" + codFabricante +
                ", fabricante='" + fabricante + '\'' +
                '}';
    }
}
