package com.dolts.controledehotel.models;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "hospedes")
public class HospedeModel implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private Date nascimento;
    @Column(unique = true)
    private String cpf;
    private String email;
    private String celular;
    private String telefone;
    private String observacao;

    public HospedeModel() {
    }

    public HospedeModel(Long id, String nome, Date nascimento, String cpf, String email, String celular, String telefone, String observacao) {
        this.id = id;
        this.nome = nome;
        this.nascimento = nascimento;
        this.cpf = cpf;
        this.email = email;
        this.celular = celular;
        this.telefone = telefone;
        this.observacao = observacao;
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

    public Date getNascimento() {
        return nascimento;
    }

    public void setNascimento(Date nascimento) {
        this.nascimento = nascimento;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String nomeMae) {
        this.observacao = nomeMae;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof HospedeModel that)) return false;

        if (!getId().equals(that.getId())) return false;
        if (!getNome().equals(that.getNome())) return false;
        if (getNascimento() != null ? !getNascimento().equals(that.getNascimento()) : that.getNascimento() != null)
            return false;
        if (!getCpf().equals(that.getCpf())) return false;
        if (getEmail() != null ? !getEmail().equals(that.getEmail()) : that.getEmail() != null) return false;
        if (getCelular() != null ? !getCelular().equals(that.getCelular()) : that.getCelular() != null) return false;
        if (getTelefone() != null ? !getTelefone().equals(that.getTelefone()) : that.getTelefone() != null)
            return false;
        return getObservacao() != null ? getObservacao().equals(that.getObservacao()) : that.getObservacao() == null;
    }

    @Override
    public int hashCode() {
        int result = getId().hashCode();
        result = 31 * result + getNome().hashCode();
        result = 31 * result + (getNascimento() != null ? getNascimento().hashCode() : 0);
        result = 31 * result + getCpf().hashCode();
        result = 31 * result + (getEmail() != null ? getEmail().hashCode() : 0);
        result = 31 * result + (getCelular() != null ? getCelular().hashCode() : 0);
        result = 31 * result + (getTelefone() != null ? getTelefone().hashCode() : 0);
        result = 31 * result + (getObservacao() != null ? getObservacao().hashCode() : 0);
        return result;
    }
}
