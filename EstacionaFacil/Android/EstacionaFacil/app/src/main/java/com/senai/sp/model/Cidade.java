package com.senai.sp.model;

public class Cidade {

    private Long codCidade;
    private String nomeCidade;
    private Estado codEstado;

    public Long getCodCidade() {
        return codCidade;
    }

    public void setCodCidade(Long codCidade) {
        this.codCidade = codCidade;
    }

    public String getNomeCidade() {
        return nomeCidade;
    }

    public void setNomeCidade(String nomeCidade) {
        this.nomeCidade = nomeCidade;
    }

    public Estado getCodEstado() {
        return codEstado;
    }

    public void setCodEstado(Estado codEstado) {
        this.codEstado = codEstado;
    }

    @Override
    public String toString() {
        return "Cidade{" +
                "codCidade=" + codCidade +
                ", nomeCidade='" + nomeCidade + '\'' +
                ", codEstado=" + codEstado +
                '}';
    }
}
