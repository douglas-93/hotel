package com.dolts.controledehotel.services;

import com.dolts.controledehotel.models.ConsumoModel;
import com.dolts.controledehotel.models.ProdutoModel;
import com.dolts.controledehotel.repositories.ConsumoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConsumoService {
    @Autowired
    private ConsumoRepository consumoRepository;
    @Autowired
    private ProdutoService produtoService;
    @Autowired
    private EstoqueService estoqueService;

    public List<ConsumoModel> findAll() {
        return consumoRepository.findAll();
    }

    public List<ConsumoModel> findByReserva(Long id) {
        return consumoRepository.findByReservaId(id);
    }

    public ConsumoModel createConsumo(ConsumoModel consumoModel) {
        Long produtoId = consumoModel.getProdutoId();
        int quantidade = consumoModel.getQuantidade();

        // Registra a saída no estoque
        estoqueService.darSaidaEstoque(produtoId, quantidade);
        return consumoRepository.save(consumoModel);
    }

    public void removeConsumo(Long id) {
        consumoRepository.deleteById(id);
    }
}
