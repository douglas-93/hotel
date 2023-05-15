package com.dolts.controledehotel.enumerators;

public enum CategoriasEnum {

    ECONOMIC(1),
    STANDARD(2),
    MASTER(3),
    DELUXE(4);

    private final Integer valor;

    CategoriasEnum(Integer valor) {
        this.valor = valor;
    }

    public Integer getValor() {
        return valor;
    }
}
