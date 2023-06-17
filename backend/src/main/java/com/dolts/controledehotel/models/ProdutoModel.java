package com.dolts.controledehotel.models;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table(name = "produtos")
public class ProdutoModel implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private Double valor;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ProdutoModel that)) return false;

        if (!getId().equals(that.getId())) return false;
        if (getNome() != null ? !getNome().equals(that.getNome()) : that.getNome() != null) return false;
        return getValor() != null ? getValor().equals(that.getValor()) : that.getValor() == null;
    }

    @Override
    public int hashCode() {
        int result = getId().hashCode();
        result = 31 * result + (getNome() != null ? getNome().hashCode() : 0);
        result = 31 * result + (getValor() != null ? getValor().hashCode() : 0);
        return result;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }
}
