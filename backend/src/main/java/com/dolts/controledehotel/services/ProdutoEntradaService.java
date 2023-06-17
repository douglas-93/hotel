package com.dolts.controledehotel.services;

import com.dolts.controledehotel.models.ProdutoEntradaModel;
import com.dolts.controledehotel.repositories.ProdutoEntradaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProdutoEntradaService {

    private final ProdutoEntradaRepository produtoEntradaRepository;

    @Autowired
    public ProdutoEntradaService(ProdutoEntradaRepository produtoEntradaRepository) {
        this.produtoEntradaRepository = produtoEntradaRepository;
    }

    public ProdutoEntradaModel salvarProdutoEntrada(ProdutoEntradaModel produtoEntrada) {
        return produtoEntradaRepository.save(produtoEntrada);
    }

    public Optional<ProdutoEntradaModel> buscarProdutoEntradaPorId(Long id) {
        return produtoEntradaRepository.findById(id);
    }

    public List<ProdutoEntradaModel> buscarTodosProdutosEntrada() {
        return produtoEntradaRepository.findAll();
    }

    public void removerProdutoEntrada(Long id) {
        produtoEntradaRepository.deleteById(id);
    }
}
