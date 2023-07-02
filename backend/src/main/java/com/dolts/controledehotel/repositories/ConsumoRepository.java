package com.dolts.controledehotel.repositories;

import com.dolts.controledehotel.models.ConsumoModel;
import com.dolts.controledehotel.models.ReservaModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ConsumoRepository extends JpaRepository<ConsumoModel, Long> {
    List<ConsumoModel> findByReservaId(Long id);
}
