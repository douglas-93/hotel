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
    private List<ProdutoModel> produtos;
    private Double valor;

}
