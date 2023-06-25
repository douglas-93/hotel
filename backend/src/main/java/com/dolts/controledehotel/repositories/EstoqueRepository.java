package com.dolts.controledehotel.repositories;

import com.dolts.controledehotel.models.EstoqueModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EstoqueRepository extends JpaRepository<EstoqueModel, Long> {
}

