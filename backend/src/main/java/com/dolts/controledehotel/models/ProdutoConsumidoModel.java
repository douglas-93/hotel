package com.dolts.controledehotel.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
public class ProdutoConsumidoModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private ProdutoModel produto;

    @ManyToOne
    @JsonIgnore
    private CupomConsumoModel cupomConsumo;

    private int quantidade;
    private double valor;

    public ProdutoConsumidoModel() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ProdutoModel getProduto() {
        return produto;
    }

    public void setProduto(ProdutoModel produto) {
        this.produto = produto;
    }

    public CupomConsumoModel getCupomConsumo() {
        return cupomConsumo;
    }

    public void setCupomConsumo(CupomConsumoModel cupomConsumo) {
        this.cupomConsumo = cupomConsumo;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ProdutoConsumidoModel that)) return false;

        if (getQuantidade() != that.getQuantidade()) return false;
        if (!getId().equals(that.getId())) return false;
        if (getProduto() != null ? !getProduto().equals(that.getProduto()) : that.getProduto() != null) return false;
        return getCupomConsumo() != null ? getCupomConsumo().equals(that.getCupomConsumo()) : that.getCupomConsumo() == null;
    }

    @Override
    public int hashCode() {
        int result = getId().hashCode();
        result = 31 * result + (getProduto() != null ? getProduto().hashCode() : 0);
        result = 31 * result + (getCupomConsumo() != null ? getCupomConsumo().hashCode() : 0);
        result = 31 * result + getQuantidade();
        return result;
    }
}
