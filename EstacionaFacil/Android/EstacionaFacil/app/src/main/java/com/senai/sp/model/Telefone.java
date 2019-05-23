package com.senai.sp.model;

public class Telefone {
    private Long codTelefone;
    private String telefone;

    public Long getCodTelefone() {
        return codTelefone;
    }

    public void setCodTelefone(Long codTelefone) {
        this.codTelefone = codTelefone;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    @Override
    public String toString() {
        return "Telefone{" +
                "codTelefone=" + codTelefone +
                ", telefone='" + telefone + '\'' +
                '}';
    }
}
