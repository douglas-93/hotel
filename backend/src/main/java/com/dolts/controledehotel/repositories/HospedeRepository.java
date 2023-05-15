package com.dolts.controledehotel.repositories;

import com.dolts.controledehotel.models.HospedeModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HospedeRepository extends JpaRepository<HospedeModel, Long> {
}
