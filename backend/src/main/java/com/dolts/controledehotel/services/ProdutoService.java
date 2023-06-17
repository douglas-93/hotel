package com.dolts.controledehotel.services;

import com.dolts.controledehotel.models.ProdutoModel;
import com.dolts.controledehotel.repositories.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    public List<ProdutoModel> findAll() {
        return produtoRepository.findAll();
    }

    public ProdutoModel findById(Long id) {
        Optional<ProdutoModel> produto = produtoRepository.findById(id);
        return produto.orElse(null);
    }

    public ProdutoModel insert(ProdutoModel novoProduto) {
        return produtoRepository.save(novoProduto);
    }

    public List<ProdutoModel> insertMany(List<ProdutoModel> novosProdutos) {
        return produtoRepository.saveAll(novosProdutos);
    }

    public void delete(Long id) {
        produtoRepository.deleteById(id);
    }

    public ProdutoModel update(Long id, ProdutoModel produtoAlterado) {
        ProdutoModel produto = produtoRepository.findById(id).orElse(null);
        if (produto != null) {
            produto.setNome(produtoAlterado.getNome());
            produto.setValor(produtoAlterado.getValor());
            return produtoRepository.save(produto);
        }
        return null;
    }
}
