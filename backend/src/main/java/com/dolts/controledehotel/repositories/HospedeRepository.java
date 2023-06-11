package com.dolts.controledehotel.repositories;

import com.dolts.controledehotel.models.HospedeModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HospedeRepository extends JpaRepository<HospedeModel, Long> {
    List<HospedeModel> findByNomeContainsIgnoreCase(String nome);
    List<HospedeModel> findByCpfContains(String cpf);
    List<HospedeModel> findByNomeContainsIgnoreCaseAndCpfContains(String nome, String cpf);
}
