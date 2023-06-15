package com.dolts.controledehotel.models;

import jakarta.persistence.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "entradas")
public class EntradaModel implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nota;
    private LocalDate dataEntrada;
    private Double valor;
    @ManyToMany(cascade = CascadeType.ALL)
    private List<ProdutoModel> produtos;

}
