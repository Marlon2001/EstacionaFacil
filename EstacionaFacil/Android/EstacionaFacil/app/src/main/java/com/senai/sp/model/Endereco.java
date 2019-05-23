package com.senai.sp.model;

public class Endereco{
    private Long codEndereco;
    private String logradouro;
    private String numero;
    private String bairro;
    private Cidade codCidade;

    public Long getCodEndereco() {
        return codEndereco;
    }

    public void setCodEndereco(Long codEndereco) {
        this.codEndereco = codEndereco;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public Cidade getCodCidade() {
        return codCidade;
    }

    public void setCodCidade(Cidade codCidade) {
        this.codCidade = codCidade;
    }

    @Override
    public String toString() {
        return "Endereco{" +
                "codEndereco=" + codEndereco +
                ", logradouro='" + logradouro + '\'' +
                ", numero='" + numero + '\'' +
                ", bairro='" + bairro + '\'' +
                ", codCidade=" + codCidade +
                '}';
    }
}
