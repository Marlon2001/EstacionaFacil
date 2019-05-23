package com.senai.sp.model;

public class Preco {
    private Long id;
    private Double valorHora1;
    private Double valorDemaisHoras;
    private String dataFim;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getValorHora1() {
        return valorHora1;
    }

    public void setValorHora1(Double valorHora1) {
        this.valorHora1 = valorHora1;
    }

    public Double getValorDemaisHoras() {
        return valorDemaisHoras;
    }

    public void setValorDemaisHoras(Double valorDemaisHoras) {
        this.valorDemaisHoras = valorDemaisHoras;
    }

    public String getDataFim() {
        return dataFim;
    }

    public void setDataFim(String dataFim) {
        this.dataFim = dataFim;
    }
}
