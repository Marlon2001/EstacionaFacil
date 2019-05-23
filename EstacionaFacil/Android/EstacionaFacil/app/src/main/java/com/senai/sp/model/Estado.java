package com.senai.sp.model;

public class Estado {
    private Long codEstado;
    private String nomeEstado;
    private String sigla;

    public Long getCodEstado() {
        return codEstado;
    }

    public void setCodEstado(Long codEstado) {
        this.codEstado = codEstado;
    }

    public String getNomeEstado() {
        return nomeEstado;
    }

    public void setNomeEstado(String nomeEstado) {
        this.nomeEstado = nomeEstado;
    }

    public String getSigla() {
        return sigla;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
    }

    @Override
    public String toString() {
        return "Estado{" +
                "codEstado=" + codEstado +
                ", nomeEstado='" + nomeEstado + '\'' +
                ", sigla='" + sigla + '\'' +
                '}';
    }
}
