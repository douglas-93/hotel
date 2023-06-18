package com.dolts.controledehotel.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table(name = "entradas")
public class EntradaModel implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "produto_id") // Adiciona a coluna de chave estrangeira no banco de dados
    @NotNull
    private ProdutoModel produto;
    private Integer quantidade;
    private String nota;
    private LocalDate dataEntrada;

    public EntradaModel() {
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

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof EntradaModel that)) return false;

        if (!getId().equals(that.getId())) return false;
        if (!getProduto().equals(that.getProduto())) return false;
        if (getQuantidade() != null ? !getQuantidade().equals(that.getQuantidade()) : that.getQuantidade() != null)
            return false;
        if (getNota() != null ? !getNota().equals(that.getNota()) : that.getNota() != null) return false;
        return getDataEntrada() != null ? getDataEntrada().equals(that.getDataEntrada()) : that.getDataEntrada() == null;
    }

    @Override
    public int hashCode() {
        int result = getId().hashCode();
        result = 31 * result + getProduto().hashCode();
        result = 31 * result + (getQuantidade() != null ? getQuantidade().hashCode() : 0);
        result = 31 * result + (getNota() != null ? getNota().hashCode() : 0);
        result = 31 * result + (getDataEntrada() != null ? getDataEntrada().hashCode() : 0);
        return result;
    }
}
