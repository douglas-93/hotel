package com.dolts.controledehotel.repositories;

import com.dolts.controledehotel.models.NotaModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotaRepository extends JpaRepository<NotaModel, Long> {
}
