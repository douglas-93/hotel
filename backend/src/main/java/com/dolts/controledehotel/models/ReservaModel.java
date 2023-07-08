package com.dolts.controledehotel.models;

import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

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
    @Column
    @Temporal(TemporalType.DATE)
    private Date dataEntrada;
    @Column
    @Temporal(TemporalType.DATE)
    private Date dataSaida;
    private String observacao;

    @OneToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    private QuartoModel quarto;

    @ManyToMany
    @JoinTable(name = "reserva_hospede", joinColumns = @JoinColumn(name = "reserva_id"), inverseJoinColumns = @JoinColumn(name = "hospede_id"))
    @OnDelete(action = OnDeleteAction.CASCADE)
    private List<HospedeModel> hospedes;

    private boolean checkIn;
    private boolean checkOut;
    private boolean cancelada;
    private String motivoCancelamento;

    public ReservaModel() {
        this.hospedes = new ArrayList<>();
    }

    public String getMotivoCancelamento() {
        return motivoCancelamento;
    }

    public void setMotivoCancelamento(String motivoCancelamento) {
        this.motivoCancelamento = motivoCancelamento;
    }

    public boolean isCancelada() {
        return cancelada;
    }

    public void setCancelada(boolean cancelada) {
        this.cancelada = cancelada;
    }

    public boolean isCheckedIn() {
        return checkIn;
    }

    public void setCheckIn(boolean checkIn) {
        this.checkIn = checkIn;
    }

    public boolean isCheckedOut() {
        return checkOut;
    }

    public void setCheckOut(boolean checkOut) {
        this.checkOut = checkOut;
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

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public boolean isCheckIn() {
        return checkIn;
    }

    public boolean isCheckOut() {
        return checkOut;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ReservaModel that)) return false;

        if (checkIn != that.checkIn) return false;
        if (checkOut != that.checkOut) return false;
        if (isCancelada() != that.isCancelada()) return false;
        if (!getId().equals(that.getId())) return false;
        if (getDataEntrada() != null ? !getDataEntrada().equals(that.getDataEntrada()) : that.getDataEntrada() != null)
            return false;
        if (getDataSaida() != null ? !getDataSaida().equals(that.getDataSaida()) : that.getDataSaida() != null)
            return false;
        if (getObservacao() != null ? !getObservacao().equals(that.getObservacao()) : that.getObservacao() != null)
            return false;
        if (getQuarto() != null ? !getQuarto().equals(that.getQuarto()) : that.getQuarto() != null) return false;
        if (getHospedes() != null ? !getHospedes().equals(that.getHospedes()) : that.getHospedes() != null)
            return false;
        return getMotivoCancelamento() != null ? getMotivoCancelamento().equals(that.getMotivoCancelamento()) : that.getMotivoCancelamento() == null;
    }

    @Override
    public int hashCode() {
        int result = getId().hashCode();
        result = 31 * result + (getDataEntrada() != null ? getDataEntrada().hashCode() : 0);
        result = 31 * result + (getDataSaida() != null ? getDataSaida().hashCode() : 0);
        result = 31 * result + (getObservacao() != null ? getObservacao().hashCode() : 0);
        result = 31 * result + (getQuarto() != null ? getQuarto().hashCode() : 0);
        result = 31 * result + (getHospedes() != null ? getHospedes().hashCode() : 0);
        result = 31 * result + (checkIn ? 1 : 0);
        result = 31 * result + (checkOut ? 1 : 0);
        result = 31 * result + (isCancelada() ? 1 : 0);
        result = 31 * result + (getMotivoCancelamento() != null ? getMotivoCancelamento().hashCode() : 0);
        return result;
    }
}
