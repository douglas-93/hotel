package com.dolts.controledehotel.repositories;

import com.dolts.controledehotel.models.EntradaModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EntradaRepository extends JpaRepository<EntradaModel, Long> {
}
