package com.dolts.controledehotel.models;

import jakarta.persistence.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
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
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "entrada_id")
    private List<ProdutoEntradaModel> produtos;

    public EntradaModel() {
        produtos = new ArrayList<>();
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

    public List<ProdutoEntradaModel> getProdutos() {
        return produtos;
    }

    public void setProdutos(List<ProdutoEntradaModel> produtos) {
        this.produtos = produtos;
    }

    public void addProduto(ProdutoModel produto, int quantidade) {
        ProdutoEntradaModel produtoEntrada = new ProdutoEntradaModel();
        produtoEntrada.setProduto(produto);
        produtoEntrada.setQuantidade(quantidade);
        produtos.add(produtoEntrada);
    }

    public void removeProduto(ProdutoModel produto) {
        produtos.removeIf(p -> p.getProduto().equals(produto));
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
