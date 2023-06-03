package com.dolts.controledehotel.repositories;

import com.dolts.controledehotel.models.QuartoModel;
import com.dolts.controledehotel.models.ReservaModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface ReservaRepository extends JpaRepository<ReservaModel, Long> {
    List<ReservaModel> findReservaModelsByQuartoAndDataEntradaBetweenOrDataSaidaBetween(QuartoModel quarto, Date dataEntrada, Date dataEntrada2, Date dataSaida, Date dataSaida2);
}
