package com.dolts.controledehotel.repositories;

import com.dolts.controledehotel.models.HotelModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface HotelRepository extends JpaRepository<HotelModel, Integer> {
    @Query("SELECT MAX(H.id) FROM HotelModel H")
    Integer findLastId();
}
