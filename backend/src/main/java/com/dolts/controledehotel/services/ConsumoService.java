package com.dolts.controledehotel.services;

import com.dolts.controledehotel.models.ConsumoModel;
import com.dolts.controledehotel.repositories.ConsumoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConsumoService {
    @Autowired
    private ConsumoRepository consumoRepository;

    public List<ConsumoModel> findAll() {
        return consumoRepository.findAll();
    }

    public List<ConsumoModel> findByReserva(Long id) {
        return consumoRepository.findByReservaId(id);
    }

    public ConsumoModel createConsumo(ConsumoModel consumoModel) {
        return consumoRepository.save(consumoModel);
    }

    public void removeConsumo(Long id) {
        consumoRepository.deleteById(id);
    }
}
