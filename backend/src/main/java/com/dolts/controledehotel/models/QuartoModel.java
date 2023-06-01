package com.dolts.controledehotel.models;

import com.dolts.controledehotel.enumerators.CategoriasEnum;
import com.dolts.controledehotel.enumerators.TiposEnum;
import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table(name = "quartos")
public class QuartoModel implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    @Enumerated(EnumType.ORDINAL)
    private TiposEnum tipo;
    @Enumerated(EnumType.ORDINAL)
    private CategoriasEnum categoria;
    private boolean ativo = true;
    @Lob
    private byte[] imagem;

    private String imagemURL;

    public QuartoModel() {
    }

    public QuartoModel(Long id, String nome, TiposEnum tipo, CategoriasEnum categoria) {
        this.id = id;
        this.nome = nome;
        this.tipo = tipo;
        this.categoria = categoria;
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
}
