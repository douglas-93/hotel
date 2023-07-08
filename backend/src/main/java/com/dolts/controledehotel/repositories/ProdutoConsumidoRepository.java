package com.dolts.controledehotel.repositories;

import com.dolts.controledehotel.models.ProdutoConsumidoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdutoConsumidoRepository extends JpaRepository<ProdutoConsumidoModel, Long> {
}
