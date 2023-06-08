package com.dolts.controledehotel.repositories;

import com.dolts.controledehotel.models.ReservaModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface ReservaRepository extends JpaRepository<ReservaModel, Long> {

    @Query("SELECT r FROM ReservaModel r WHERE ((:entrada BETWEEN r.dataEntrada AND r.dataSaida) OR (:saida BETWEEN r.dataEntrada AND r.dataSaida)" +
            " OR (r.dataEntrada BETWEEN :entrada AND :saida) OR (r.dataSaida BETWEEN :entrada AND :saida)) AND r.quarto.id = :quartoId ORDER BY r.dataEntrada DESC")
    List<ReservaModel> findByDateAndQuartoId(Date entrada, Date saida, Long quartoId);
}
