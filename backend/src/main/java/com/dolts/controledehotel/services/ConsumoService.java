package com.dolts.controledehotel.services;

import com.dolts.controledehotel.models.ConsumoModel;
import com.dolts.controledehotel.repositories.ConsumoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ConsumoService {

    @Autowired
    private ConsumoRepository consumoRepository;

    public List<ConsumoModel> findAll() {
        return consumoRepository.findAll();
    }

    public ConsumoModel findById(Long id) {
        Optional<ConsumoModel> consumo = consumoRepository.findById(id);
        return consumo.orElse(null);
    }

    public ConsumoModel insert(ConsumoModel novoConsumo) {
        return consumoRepository.save(novoConsumo);
    }

    public List<ConsumoModel> insertMany(List<ConsumoModel> novosConsumos) {
        return consumoRepository.saveAll(novosConsumos);
    }

    public void delete(Long id) {
        consumoRepository.deleteById(id);
    }

    public ConsumoModel update(Long id, ConsumoModel consumoAlterado) {
        ConsumoModel consumo = consumoRepository.findById(id).orElse(null);
        if (consumo != null) {
            consumo.setNota(consumoAlterado.getNota());
            consumo.setProduto(consumoAlterado.getProduto());
            consumo.setQuantidade(consumoAlterado.getQuantidade());
            return consumoRepository.save(consumo);
        }
        return null;
    }
}
