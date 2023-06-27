package com.dolts.controledehotel.dtos;

import java.util.ArrayList;
import java.util.List;

public class CupomConsumoRequest {
    private double valorDiariaHospede;
    private List<Long> produtosIds = new ArrayList<>();
    private List<Integer> quantidades = new ArrayList<>();

    public CupomConsumoRequest() {
    }

    public double getValorDiariaHospede() {
        return valorDiariaHospede;
    }

    public void setValorDiariaHospede(double valorDiariaHospede) {
        this.valorDiariaHospede = valorDiariaHospede;
    }

    public List<Long> getProdutosIds() {
        return produtosIds;
    }

    public void setProdutosIds(List<Long> produtosIds) {
        this.produtosIds = produtosIds;
    }

    public List<Integer> getQuantidades() {
        return quantidades;
    }

    public void setQuantidades(List<Integer> quantidades) {
        this.quantidades = quantidades;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CupomConsumoRequest that)) return false;

        if (Double.compare(that.getValorDiariaHospede(), getValorDiariaHospede()) != 0) return false;
        if (getProdutosIds() != null ? !getProdutosIds().equals(that.getProdutosIds()) : that.getProdutosIds() != null)
            return false;
        return getQuantidades() != null ? getQuantidades().equals(that.getQuantidades()) : that.getQuantidades() == null;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        temp = Double.doubleToLongBits(getValorDiariaHospede());
        result = (int) (temp ^ (temp >>> 32));
        result = 31 * result + (getProdutosIds() != null ? getProdutosIds().hashCode() : 0);
        result = 31 * result + (getQuantidades() != null ? getQuantidades().hashCode() : 0);
        return result;
    }
}

