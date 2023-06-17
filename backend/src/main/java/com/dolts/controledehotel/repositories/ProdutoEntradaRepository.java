package com.dolts.controledehotel.repositories;

import com.dolts.controledehotel.models.ProdutoEntradaModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdutoEntradaRepository extends JpaRepository<ProdutoEntradaModel, Long> {
}
