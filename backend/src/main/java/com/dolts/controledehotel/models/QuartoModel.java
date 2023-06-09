package com.dolts.controledehotel.models;

import com.dolts.controledehotel.enumerators.CategoriasEnum;
import com.dolts.controledehotel.enumerators.TiposEnum;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Entity
@Table(name = "quartos")
public class QuartoModel implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String nome;
    @Enumerated(EnumType.ORDINAL)
    private TiposEnum tipo;
    @Enumerated(EnumType.ORDINAL)
    private CategoriasEnum categoria;
    private boolean ativo = true;
    @Lob
    private byte[] imagem;

    private String imagemURL;
    private Double valor;
    @ElementCollection
    private List<String> itens = new ArrayList<>();

    public QuartoModel() {
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public List<String> getItens() {
        return itens;
    }

    public void setItens(List<String> itens) {
        this.itens = itens;
    }

    public byte[] getImagem() {
        return imagem;
    }

    public void setImagem(byte[] imagem) {
        this.imagem = imagem;
    }

    public String getImagemURL() {
        return imagemURL;
    }

    public void setImagemURL(String imagemURL) {
        this.imagemURL = imagemURL;
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

    public TiposEnum getTipo() {
        return tipo;
    }

    public void setTipo(TiposEnum tipo) {
        this.tipo = tipo;
    }

    public CategoriasEnum getCategoria() {
        return categoria;
    }

    public void setCategoria(CategoriasEnum categoria) {
        this.categoria = categoria;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof QuartoModel that)) return false;

        if (isAtivo() != that.isAtivo()) return false;
        if (!getId().equals(that.getId())) return false;
        if (!getNome().equals(that.getNome())) return false;
        if (getTipo() != that.getTipo()) return false;
        if (getCategoria() != that.getCategoria()) return false;
        if (!Arrays.equals(getImagem(), that.getImagem())) return false;
        if (getImagemURL() != null ? !getImagemURL().equals(that.getImagemURL()) : that.getImagemURL() != null)
            return false;
        if (getValor() != null ? !getValor().equals(that.getValor()) : that.getValor() != null) return false;
        return getItens() != null ? getItens().equals(that.getItens()) : that.getItens() == null;
    }

    @Override
    public int hashCode() {
        int result = getId().hashCode();
        result = 31 * result + getNome().hashCode();
        result = 31 * result + (getTipo() != null ? getTipo().hashCode() : 0);
        result = 31 * result + (getCategoria() != null ? getCategoria().hashCode() : 0);
        result = 31 * result + (isAtivo() ? 1 : 0);
        result = 31 * result + Arrays.hashCode(getImagem());
        result = 31 * result + (getImagemURL() != null ? getImagemURL().hashCode() : 0);
        result = 31 * result + (getValor() != null ? getValor().hashCode() : 0);
        result = 31 * result + (getItens() != null ? getItens().hashCode() : 0);
        return result;
    }
}
