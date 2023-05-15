package com.dolts.controledehotel.repositories;

import com.dolts.controledehotel.models.ReservaModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservaRepository extends JpaRepository<ReservaModel, Long> {
}
