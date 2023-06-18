package com.dolts.controledehotel.services;

import com.dolts.controledehotel.models.EntradaModel;
import com.dolts.controledehotel.models.ProdutoModel;
import com.dolts.controledehotel.repositories.EntradaRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EntradaService {
    @Autowired
    private EntradaRepository entradaRepository;

    public List<EntradaModel> listarEntradas() {
        Sort sortById = Sort.by(Sort.Direction.ASC, "id");
        return entradaRepository.findAll(sortById);
    }

    public Optional<EntradaModel> buscarEntradaPorId(Long id) {
        return entradaRepository.findById(id);
    }

    public EntradaModel salvarEntrada(EntradaModel entrada) {
        return entradaRepository.save(entrada);
    }

    public void removerEntrada(Long id) {
        entradaRepository.deleteById(id);
    }
}
