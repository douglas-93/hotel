package com.dolts.controledehotel.repositories;

import com.dolts.controledehotel.enumerators.CategoriasEnum;
import com.dolts.controledehotel.enumerators.TiposEnum;
import com.dolts.controledehotel.models.QuartoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuartoRepository extends JpaRepository<QuartoModel, Long> {
    List<QuartoModel> findByNomeContainsIgnoreCase(String nome);
    List<QuartoModel> findByAtivo(Boolean ativo);
    List<QuartoModel> findByTipo(TiposEnum tipo);
    List<QuartoModel> findByCategoria(CategoriasEnum categoria);
}
