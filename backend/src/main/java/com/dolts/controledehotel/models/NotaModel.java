package com.dolts.controledehotel.models;

import jakarta.persistence.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "notas")
public class NotaModel implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate dataEmissao;
    private Integer diasDeHospedagem;
    @OneToMany(cascade = CascadeType.ALL)
    private List<HospedeModel> hospedes;
    @ManyToOne
    private QuartoModel quarto;
    @ManyToMany(cascade = CascadeType.ALL)
    private List<ConsumoModel> consumos;
    private Double valor;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDataEmissao() {
        return dataEmissao;
    }

    public void setDataEmissao(LocalDate dataEmissao) {
        this.dataEmissao = dataEmissao;
    }

    public Integer getDiasDeHospedagem() {
        return diasDeHospedagem;
    }

    public void setDiasDeHospedagem(Integer diasDeHospedagem) {
        this.diasDeHospedagem = diasDeHospedagem;
    }

    public List<HospedeModel> getHospedes() {
        return hospedes;
    }

    public void setHospedes(List<HospedeModel> hospedes) {
        this.hospedes = hospedes;
    }

    public QuartoModel getQuarto() {
        return quarto;
    }

    public void setQuarto(QuartoModel quarto) {
        this.quarto = quarto;
    }

    public List<ConsumoModel> getConsumos() {
        return consumos;
    }

    public void setConsumos(List<ConsumoModel> consumos) {
        this.consumos = consumos;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof NotaModel notaModel)) return false;

        if (!getId().equals(notaModel.getId())) return false;
        if (getDataEmissao() != null ? !getDataEmissao().equals(notaModel.getDataEmissao()) : notaModel.getDataEmissao() != null)
            return false;
        if (getDiasDeHospedagem() != null ? !getDiasDeHospedagem().equals(notaModel.getDiasDeHospedagem()) : notaModel.getDiasDeHospedagem() != null)
            return false;
        if (getHospedes() != null ? !getHospedes().equals(notaModel.getHospedes()) : notaModel.getHospedes() != null)
            return false;
        if (getQuarto() != null ? !getQuarto().equals(notaModel.getQuarto()) : notaModel.getQuarto() != null)
            return false;
        if (getConsumos() != null ? !getConsumos().equals(notaModel.getConsumos()) : notaModel.getConsumos() != null)
            return false;
        return getValor() != null ? getValor().equals(notaModel.getValor()) : notaModel.getValor() == null;
    }

    @Override
    public int hashCode() {
        int result = getId().hashCode();
        result = 31 * result + (getDataEmissao() != null ? getDataEmissao().hashCode() : 0);
        result = 31 * result + (getDiasDeHospedagem() != null ? getDiasDeHospedagem().hashCode() : 0);
        result = 31 * result + (getHospedes() != null ? getHospedes().hashCode() : 0);
        result = 31 * result + (getQuarto() != null ? getQuarto().hashCode() : 0);
        result = 31 * result + (getConsumos() != null ? getConsumos().hashCode() : 0);
        result = 31 * result + (getValor() != null ? getValor().hashCode() : 0);
        return result;
    }
}
