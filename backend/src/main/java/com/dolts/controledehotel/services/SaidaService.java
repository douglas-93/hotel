package com.dolts.controledehotel.services;

import com.dolts.controledehotel.models.SaidaModel;
import com.dolts.controledehotel.repositories.SaidaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SaidaService {
    @Autowired
    private SaidaRepository saidaRepository;

    public List<SaidaModel> listarSaidas() {
        Sort sortById = Sort.by(Sort.Direction.ASC, "id");
        return saidaRepository.findAll(sortById);
    }

    public Optional<SaidaModel> buscarSaidaPorId(Long id) {
        return saidaRepository.findById(id);
    }

    public SaidaModel salvarSaida(SaidaModel saida) {
        return saidaRepository.save(saida);
    }

    public void removerSaida(Long id) {
        saidaRepository.deleteById(id);
    }
}
