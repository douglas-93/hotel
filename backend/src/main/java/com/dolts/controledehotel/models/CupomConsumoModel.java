package com.dolts.controledehotel.models;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
public class CupomConsumoModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private double valorDiariaHospede;
    private LocalDateTime dataValidade;

    @OneToMany(mappedBy = "cupomConsumo", cascade = CascadeType.ALL)
    private List<ProdutoConsumidoModel> produtosConsumidos;

    public CupomConsumoModel() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getValorDiariaHospede() {
        return valorDiariaHospede;
    }

    public void setValorDiariaHospede(double valorDiariaHospede) {
        this.valorDiariaHospede = valorDiariaHospede;
    }

    public LocalDateTime getDataValidade() {
        return dataValidade;
    }

    public void setDataValidade(LocalDateTime dataValidade) {
        this.dataValidade = dataValidade;
    }

    public List<ProdutoConsumidoModel> getProdutosConsumidos() {
        return produtosConsumidos;
    }

    public void setProdutosConsumidos(List<ProdutoConsumidoModel> produtosConsumidos) {
        this.produtosConsumidos = produtosConsumidos;
    }
}

