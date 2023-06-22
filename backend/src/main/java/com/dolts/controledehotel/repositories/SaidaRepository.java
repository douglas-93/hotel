package com.dolts.controledehotel.repositories;

import com.dolts.controledehotel.models.SaidaModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SaidaRepository extends JpaRepository<SaidaModel, Long> {
}
