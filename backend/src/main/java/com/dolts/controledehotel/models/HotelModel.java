package com.dolts.controledehotel.models;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table(name = "hoteis")
public class HotelModel implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nome;
    private String cnpj;
    private String logradouro;
    private String numero;
    private String cidade;
    private String estado;
    private String pais;

    public HotelModel() {
    }

    public HotelModel(Integer id, String nome, String cnpj, String logradouro, String numero, String cidade, String estado, String pais) {
        this.id = id;
        this.nome = nome;
        this.cnpj = cnpj;
        this.logradouro = logradouro;
        this.numero = numero;
        this.cidade = cidade;
        this.estado = estado;
        this.pais = pais;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof HotelModel that)) return false;

        if (!getId().equals(that.getId())) return false;
        if (getNome() != null ? !getNome().equals(that.getNome()) : that.getNome() != null) return false;
        if (getCnpj() != null ? !getCnpj().equals(that.getCnpj()) : that.getCnpj() != null) return false;
        if (getLogradouro() != null ? !getLogradouro().equals(that.getLogradouro()) : that.getLogradouro() != null)
            return false;
        if (getNumero() != null ? !getNumero().equals(that.getNumero()) : that.getNumero() != null) return false;
        if (getCidade() != null ? !getCidade().equals(that.getCidade()) : that.getCidade() != null) return false;
        if (getEstado() != null ? !getEstado().equals(that.getEstado()) : that.getEstado() != null) return false;
        return getPais() != null ? getPais().equals(that.getPais()) : that.getPais() == null;
    }

    @Override
    public int hashCode() {
        int result = getId().hashCode();
        result = 31 * result + (getNome() != null ? getNome().hashCode() : 0);
        result = 31 * result + (getCnpj() != null ? getCnpj().hashCode() : 0);
        result = 31 * result + (getLogradouro() != null ? getLogradouro().hashCode() : 0);
        result = 31 * result + (getNumero() != null ? getNumero().hashCode() : 0);
        result = 31 * result + (getCidade() != null ? getCidade().hashCode() : 0);
        result = 31 * result + (getEstado() != null ? getEstado().hashCode() : 0);
        result = 31 * result + (getPais() != null ? getPais().hashCode() : 0);
        return result;
    }
}
