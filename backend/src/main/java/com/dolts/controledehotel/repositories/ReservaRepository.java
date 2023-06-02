package com.dolts.controledehotel.repositories;

import com.dolts.controledehotel.models.QuartoModel;
import com.dolts.controledehotel.models.ReservaModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface ReservaRepository extends JpaRepository<ReservaModel, Long> {
    List<ReservaModel> findAllByQuartoAndDataEntrada(QuartoModel quarto, Date dataEntrada);

    List<ReservaModel> findByQuartoAndDataEntradaBetween(QuartoModel quarto, Date dataInicio, Date dataFim);
}
