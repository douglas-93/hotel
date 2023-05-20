package com.dolts.controledehotel.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "reservas")
public class ReservaModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToMany(orphanRemoval = true, cascade = CascadeType.ALL)
    private List<HospedeModel> hospede;
    @OneToMany(orphanRemoval = true, cascade = CascadeType.ALL)
    private List<QuartoModel> quarto;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss")
    @DateTimeFormat(pattern = "dd-MM-yyyy HH:mm:ss")
    private LocalDateTime dataDeRealizacaoDaReserva = LocalDateTime.now();
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss")
    @DateTimeFormat(pattern = "dd-MM-yyyy HH:mm:ss")
    private LocalDateTime inicio;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss")
    @DateTimeFormat(pattern = "dd-MM-yyyy HH:mm:ss")
    private LocalDateTime fim;

    public ReservaModel() {
    }

    public ReservaModel(Long id, List<HospedeModel> hospede, List<QuartoModel> quarto, LocalDateTime dataDeRealizacaoDaReserva, LocalDateTime inicio, LocalDateTime fim) {
        this.id = id;
        this.hospede = hospede;
        this.quarto = quarto;
        this.dataDeRealizacaoDaReserva = dataDeRealizacaoDaReserva;
        this.inicio = inicio;
        this.fim = fim;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<HospedeModel> getHospede() {
        return hospede;
    }

    public void setHospede(List<HospedeModel> hospede) {
        this.hospede = hospede;
    }

    public List<QuartoModel> getQuarto() {
        return quarto;
    }

    public void setQuarto(List<QuartoModel> quarto) {
        this.quarto = quarto;
    }

    public LocalDateTime getDataDeRealizacaoDaReserva() {
        return dataDeRealizacaoDaReserva;
    }

    public void setDataDeRealizacaoDaReserva(LocalDateTime dataDeRealizacaoDaReserva) {
        this.dataDeRealizacaoDaReserva = dataDeRealizacaoDaReserva;
    }

    public LocalDateTime getInicio() {
        return inicio;
    }

    public void setInicio(LocalDateTime inicio) {
        this.inicio = inicio;
    }

    public LocalDateTime getFim() {
        return fim;
    }

    public void setFim(LocalDateTime fim) {
        this.fim = fim;
    }
}
