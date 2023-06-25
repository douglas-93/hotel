package com.dolts.controledehotel.repositories;

import com.dolts.controledehotel.models.CupomConsumoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CupomConsumoRepository extends JpaRepository<CupomConsumoModel, Long> {
}
