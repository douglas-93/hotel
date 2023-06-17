package com.dolts.controledehotel.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;

@Entity
@Table(name = "estoque")
public class EstoqueModel implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "produto_id", referencedColumnName = "id")
    private ProdutoModel produto;

    @NotNull
    private Integer quantidade;

    public EstoqueModel() {
    }

    public EstoqueModel(ProdutoModel produto, Integer quantidade) {
        this.produto = produto;
        this.quantidade = quantidade;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof EstoqueModel that)) return false;

        if (!id.equals(that.id)) return false;
        if (!produto.equals(that.produto)) return false;
        return quantidade.equals(that.quantidade);
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + produto.hashCode();
        result = 31 * result + quantidade.hashCode();
        return result;
    }
}
