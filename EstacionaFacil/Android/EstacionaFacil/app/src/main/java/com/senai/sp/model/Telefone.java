package com.senai.sp.model;

import java.io.Serializable;

public class Telefone implements Serializable{

    private int codTelefone;
    private String telefone;
    private String tipoTelefone;

    public String getTipoTelefone() {
        return tipoTelefone;
    }

    public void setTipoTelefone(String tipoTelefone) {
        this.tipoTelefone = tipoTelefone;
    }

    public int getCodTelefone() {
        return codTelefone;
    }

    public void setCodTelefone(int codTelefone) {
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
