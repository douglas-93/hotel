package com.dolts.controledehotel.models;

import jakarta.persistence.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "entradas")
public class EntradaModel implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nota;
    private LocalDate dataEntrada;
    private Double valor;
    @ManyToMany(cascade = CascadeType.ALL)
    private List<ProdutoModel> produtos;

    public EntradaModel() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNota() {
        return nota;
    }

    public void setNota(String nota) {
        this.nota = nota;
    }

    public LocalDate getDataEntrada() {
        return dataEntrada;
    }

    public void setDataEntrada(LocalDate dataEntrada) {
        this.dataEntrada = dataEntrada;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public List<ProdutoModel> getProdutos() {
        return produtos;
    }

    public void setProdutos(List<ProdutoModel> produtos) {
        this.produtos = produtos;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof EntradaModel that)) return false;

        if (!id.equals(that.id)) return false;
        if (!Objects.equals(nota, that.nota)) return false;
        if (!Objects.equals(dataEntrada, that.dataEntrada)) return false;
        if (!valor.equals(that.valor)) return false;
        return produtos.equals(that.produtos);
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + (nota != null ? nota.hashCode() : 0);
        result = 31 * result + (dataEntrada != null ? dataEntrada.hashCode() : 0);
        result = 31 * result + valor.hashCode();
        result = 31 * result + produtos.hashCode();
        return result;
    }
}
