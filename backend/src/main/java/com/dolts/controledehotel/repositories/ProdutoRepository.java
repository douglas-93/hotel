package com.dolts.controledehotel.repositories;

import com.dolts.controledehotel.models.ProdutoModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepository extends JpaRepository<ProdutoModel, Long> {
}
