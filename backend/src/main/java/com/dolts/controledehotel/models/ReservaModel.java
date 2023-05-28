package com.dolts.controledehotel.models;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "reservas")
public class ReservaModel implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date dataEntrada;
    private Date dataSaida;
    private String observacao;

    @OneToOne
    private QuartoModel quarto;

    @ManyToMany
    @JoinTable(
            name = "reserva_hospede",
            joinColumns = @JoinColumn(name = "reserva_id"),
            inverseJoinColumns = @JoinColumn(name = "hospede_id")
    )
    private List<HospedeModel> hospedes;

    public ReservaModel() {
        this.hospedes = new ArrayList<>();
    }

    public ReservaModel(Long id, Date dataEntrada, Date dataSaida, QuartoModel quarto) {
        this.id = id;
        this.dataEntrada = dataEntrada;
        this.dataSaida = dataSaida;
        this.quarto = quarto;
        this.hospedes = new ArrayList<>();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDataEntrada() {
        return dataEntrada;
    }

    public void setDataEntrada(Date dataEntrada) {
        this.dataEntrada = dataEntrada;
    }

    public Date getDataSaida() {
        return dataSaida;
    }

    public void setDataSaida(Date dataSaida) {
        this.dataSaida = dataSaida;
    }

    public QuartoModel getQuarto() {
        return quarto;
    }

    public void setQuarto(QuartoModel quarto) {
        this.quarto = quarto;
    }

    public List<HospedeModel> getHospedes() {
        return hospedes;
    }

    public void setHospedes(List<HospedeModel> hospedes) {
        this.hospedes = hospedes;
    }

    public void adicionarHospede(HospedeModel hospede) {
        hospedes.add(hospede);
    }

    public void removerHospede(HospedeModel hospede) {
        hospedes.remove(hospede);
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }
}
