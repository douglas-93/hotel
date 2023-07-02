package com.dolts.controledehotel.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
public class ConsumoModel implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    Long reservaId;
    Long produtoId;
    Integer quantidade;
    LocalDateTime dataConsumo;

    public ConsumoModel() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getReservaId() {
        return reservaId;
    }

    public void setReservaId(Long reservaId) {
        this.reservaId = reservaId;
    }

    public Long getProdutoId() {
        return produtoId;
    }

    public void setProdutoId(Long produtoId) {
        this.produtoId = produtoId;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public LocalDateTime getDataConsumo() {
        return dataConsumo;
    }

    public void setDataConsumo(LocalDateTime dataConsumo) {
        this.dataConsumo = dataConsumo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ConsumoModel that)) return false;

        if (!getId().equals(that.getId())) return false;
        if (getReservaId() != null ? !getReservaId().equals(that.getReservaId()) : that.getReservaId() != null)
            return false;
        if (getProdutoId() != null ? !getProdutoId().equals(that.getProdutoId()) : that.getProdutoId() != null)
            return false;
        if (getQuantidade() != null ? !getQuantidade().equals(that.getQuantidade()) : that.getQuantidade() != null)
            return false;
        return getDataConsumo() != null ? getDataConsumo().equals(that.getDataConsumo()) : that.getDataConsumo() == null;
    }

    @Override
    public int hashCode() {
        int result = getId().hashCode();
        result = 31 * result + (getReservaId() != null ? getReservaId().hashCode() : 0);
        result = 31 * result + (getProdutoId() != null ? getProdutoId().hashCode() : 0);
        result = 31 * result + (getQuantidade() != null ? getQuantidade().hashCode() : 0);
        result = 31 * result + (getDataConsumo() != null ? getDataConsumo().hashCode() : 0);
        return result;
    }
}
