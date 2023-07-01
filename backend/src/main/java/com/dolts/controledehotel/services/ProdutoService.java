package com.dolts.controledehotel.services;

import com.dolts.controledehotel.models.ProdutoModel;
import com.dolts.controledehotel.repositories.ProdutoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProdutoService {
    private final ProdutoRepository produtoRepository;

    public ProdutoService(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    public ProdutoModel criarProduto(ProdutoModel produto) {
        return produtoRepository.save(produto);
    }

    public List<ProdutoModel> criarProdutos(List<ProdutoModel> produtos) {
        return produtoRepository.saveAll(produtos);
    }

    public List<ProdutoModel> listarProdutos() {
        return produtoRepository.findAll();
    }

    public ProdutoModel buscarProduto(Long id) {
        return produtoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado"));
    }

    public ProdutoModel atualizarProduto(ProdutoModel produto) {
        return produtoRepository.save(produto);
    }

}
