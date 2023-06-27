package com.dolts.controledehotel.dtos;

public class MovimentacaoEstoqueRequest {
    private Long produtoId;
    private int quantidade;

    public MovimentacaoEstoqueRequest() {
    }

    public Long getProdutoId() {
        return produtoId;
    }

    public void setProdutoId(Long produtoId) {
        this.produtoId = produtoId;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MovimentacaoEstoqueRequest that)) return false;

        if (getQuantidade() != that.getQuantidade()) return false;
        return getProdutoId() != null ? getProdutoId().equals(that.getProdutoId()) : that.getProdutoId() == null;
    }

    @Override
    public int hashCode() {
        int result = getProdutoId() != null ? getProdutoId().hashCode() : 0;
        result = 31 * result + getQuantidade();
        return result;
    }
}
