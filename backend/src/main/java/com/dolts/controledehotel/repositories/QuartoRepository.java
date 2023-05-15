package com.dolts.controledehotel.repositories;

import com.dolts.controledehotel.models.QuartoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuartoRepository extends JpaRepository<QuartoModel, Long> {
}
