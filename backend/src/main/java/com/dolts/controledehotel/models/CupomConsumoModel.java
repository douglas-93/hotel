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
    private LocalDateTime dataEmissao;
    private LocalDateTime dataEntrada;
    private LocalDateTime dataSaida;

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

    public LocalDateTime getDataEmissao() {
        return dataEmissao;
    }

    public void setDataEmissao(LocalDateTime dataEmissao) {
        this.dataEmissao = dataEmissao;
    }

    public List<ProdutoConsumidoModel> getProdutosConsumidos() {
        return produtosConsumidos;
    }

    public void setProdutosConsumidos(List<ProdutoConsumidoModel> produtosConsumidos) {
        this.produtosConsumidos = produtosConsumidos;
    }

    public LocalDateTime getDataEntrada() {
        return dataEntrada;
    }

    public void setDataEntrada(LocalDateTime dataEntrada) {
        this.dataEntrada = dataEntrada;
    }

    public LocalDateTime getDataSaida() {
        return dataSaida;
    }

    public void setDataSaida(LocalDateTime dataSaida) {
        this.dataSaida = dataSaida;
    }
}

