package com.dolts.controledehotel.models;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table(name = "consumos")
public class ConsumoModel implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private NotaModel nota;

    @ManyToOne
    private ProdutoModel produto;

    private Integer quantidade;
}
