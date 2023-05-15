package com.dolts.controledehotel.enumerators;

public enum TiposEnum {
    SOLTEIRO(1),
    DUPLO_SOLTEIRO(2),
    TRIPLO_SOLTEIRO(3),
    CASAL(4),
    DORMITORIO(5),
    APARTAMENTO(6);

    private final Integer valor;

    TiposEnum(Integer valor) {
        this.valor = valor;
    }

    public Integer getValor() {
        return valor;
    }
}
