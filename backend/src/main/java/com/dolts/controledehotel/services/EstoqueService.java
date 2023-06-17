package com.dolts.controledehotel.services;

import com.dolts.controledehotel.models.EstoqueModel;
import com.dolts.controledehotel.repositories.EstoqueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EstoqueService {

    private final EstoqueRepository estoqueRepository;

    @Autowired
    public EstoqueService(EstoqueRepository estoqueRepository) {
        this.estoqueRepository = estoqueRepository;
    }

    public EstoqueModel salvarEstoque(EstoqueModel estoque) {
        return estoqueRepository.save(estoque);
    }

    public Optional<EstoqueModel> buscarEstoquePorId(Long id) {
        return estoqueRepository.findById(id);
    }

    public List<EstoqueModel> buscarTodosEstoques() {
        return estoqueRepository.findAll();
    }

    public void removerEstoque(Long id) {
        estoqueRepository.deleteById(id);
    }
}
