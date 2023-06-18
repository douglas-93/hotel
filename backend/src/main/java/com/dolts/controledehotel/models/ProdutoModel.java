package com.dolts.controledehotel.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "produtos")
public class ProdutoModel implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private Double preco;
    private Double quantidade;

    @JsonIgnore
    @OneToMany(mappedBy = "produto", fetch = FetchType.LAZY)
    private List<EntradaModel> entradas = new ArrayList<>();

    public ProdutoModel() {
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

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }

    public Double getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Double quantidade) {
        this.quantidade = quantidade;
    }

    public List<EntradaModel> getEntradas() {
        return entradas;
    }

    public void setEntradas(List<EntradaModel> entradas) {
        this.entradas = entradas;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ProdutoModel that)) return false;

        if (!getId().equals(that.getId())) return false;
        if (!getNome().equals(that.getNome())) return false;
        if (getPreco() != null ? !getPreco().equals(that.getPreco()) : that.getPreco() != null) return false;
        return getQuantidade() != null ? getQuantidade().equals(that.getQuantidade()) : that.getQuantidade() == null;
    }

    @Override
    public int hashCode() {
        int result = getId().hashCode();
        result = 31 * result + getNome().hashCode();
        result = 31 * result + (getPreco() != null ? getPreco().hashCode() : 0);
        result = 31 * result + (getQuantidade() != null ? getQuantidade().hashCode() : 0);
        return result;
    }
}
