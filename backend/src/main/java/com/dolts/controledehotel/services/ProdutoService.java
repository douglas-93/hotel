package com.dolts.controledehotel.services;

import com.dolts.controledehotel.models.ProdutoModel;
import com.dolts.controledehotel.repositories.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    public List<ProdutoModel> listarProdutos() {
        Sort sortById = Sort.by(Sort.Direction.ASC, "id");
        return produtoRepository.findAll(sortById);
    }

    public Optional<ProdutoModel> buscarProdutoPorId(Long id) {
        return produtoRepository.findById(id);
    }

    public ProdutoModel salvarProduto(ProdutoModel produto) {
        return produtoRepository.save(produto);
    }

    public void removerProduto(Long id) {
        produtoRepository.deleteById(id);
    }

    public ProdutoModel atualizarProduto(ProdutoModel produtoAtualizado) {
        Long produtoId = produtoAtualizado.getId();

        // Verificar se o ID do produto é válido
        if (produtoId == null || !produtoRepository.existsById(produtoId)) {
            throw new IllegalArgumentException("ID do produto inválido");
        }

        ProdutoModel produtoExistente = produtoRepository.findById(produtoId).orElse(null);

        if (produtoExistente != null) {
            produtoExistente.setNome(produtoAtualizado.getNome());
            produtoExistente.setPreco(produtoAtualizado.getPreco());
            produtoExistente.setQuantidade(produtoAtualizado.getQuantidade());

            produtoExistente = produtoRepository.save(produtoExistente);
        }

        return produtoExistente;
    }
}
