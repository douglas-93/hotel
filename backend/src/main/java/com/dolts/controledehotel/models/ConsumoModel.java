package com.dolts.controledehotel.models;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "consumos")
public class ConsumoModel implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private NotaModel nota;

    @ManyToOne
    private ProdutoModel produto;

    private Integer quantidade;

    public ConsumoModel() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public NotaModel getNota() {
        return nota;
    }

    public void setNota(NotaModel nota) {
        this.nota = nota;
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
        if (!(o instanceof ConsumoModel that)) return false;

        if (!id.equals(that.id)) return false;
        if (!Objects.equals(nota, that.nota)) return false;
        if (!Objects.equals(produto, that.produto)) return false;
        return Objects.equals(quantidade, that.quantidade);
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + (nota != null ? nota.hashCode() : 0);
        result = 31 * result + (produto != null ? produto.hashCode() : 0);
        result = 31 * result + (quantidade != null ? quantidade.hashCode() : 0);
        return result;
    }
}
