package com.dolts.controledehotel.services;

import com.dolts.controledehotel.models.EntradaModel;
import com.dolts.controledehotel.repositories.EntradaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EntradaService {

    @Autowired
    private EntradaRepository entradaRepository;

    public List<EntradaModel> findAll() {
        return entradaRepository.findAll();
    }

    public EntradaModel findById(Long id) {
        Optional<EntradaModel> entrada = entradaRepository.findById(id);
        return entrada.orElse(null);
    }

    public EntradaModel insert(EntradaModel novaEntrada) {
        return entradaRepository.save(novaEntrada);
    }

    public List<EntradaModel> insertMany(List<EntradaModel> novasEntradas) {
        return entradaRepository.saveAll(novasEntradas);
    }

    public void delete(Long id) {
        entradaRepository.deleteById(id);
    }

    public EntradaModel update(Long id, EntradaModel entradaAlterada) {
        EntradaModel entrada = entradaRepository.findById(id).orElse(null);
        if (entrada != null) {
            entrada.setNota(entradaAlterada.getNota());
            entrada.setDataEntrada(entradaAlterada.getDataEntrada());
            entrada.setValor(entradaAlterada.getValor());

            if (entradaAlterada.getProdutos() != null) {
                entrada.getProdutos().clear();
                entrada.getProdutos().addAll(entradaAlterada.getProdutos());
            }
            return entradaRepository.save(entrada);
        }
        return null;
    }
}
