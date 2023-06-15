package com.dolts.controledehotel.models;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table(name = "produtos")
public class ProdutoModel implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private Double valor;
}
