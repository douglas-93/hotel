package com.dolts.controledehotel.repositories;

import com.dolts.controledehotel.models.ConsumoModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConsumoRepository extends JpaRepository<ConsumoModel, Long> {
}
