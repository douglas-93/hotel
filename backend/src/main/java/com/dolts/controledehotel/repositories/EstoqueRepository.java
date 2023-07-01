package com.dolts.controledehotel.repositories;

import com.dolts.controledehotel.models.EstoqueModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EstoqueRepository extends JpaRepository<EstoqueModel, Long> {
    List<EstoqueModel> findByTipo(String tipo);
}

