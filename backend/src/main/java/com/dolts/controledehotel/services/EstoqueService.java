package com.dolts.controledehotel.services;

import com.dolts.controledehotel.models.EstoqueModel;
import com.dolts.controledehotel.models.ProdutoModel;
import com.dolts.controledehotel.repositories.EstoqueRepository;
import org.hibernate.query.sqm.SortOrder;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class EstoqueService {
    private final EstoqueRepository estoqueRepository;
    private final ProdutoService produtoService;

    public EstoqueService(EstoqueRepository estoqueRepository, ProdutoService produtoService) {
        this.estoqueRepository = estoqueRepository;
        this.produtoService = produtoService;
    }

    public EstoqueModel darEntradaEstoque(Long produtoId, int quantidade) {
        ProdutoModel produto = produtoService.buscarProduto(produtoId);
        produto.setQuantidadeEstoque(produto.getQuantidadeEstoque() + quantidade);

        EstoqueModel entradaEstoque = new EstoqueModel();
        entradaEstoque.setProduto(produto);
        entradaEstoque.setQuantidade(quantidade);
        entradaEstoque.setTipo("Entrada");
        entradaEstoque.setData(LocalDateTime.now());

        produtoService.atualizarProduto(produto);
        return estoqueRepository.save(entradaEstoque);
    }

    public EstoqueModel darSaidaEstoque(Long produtoId, int quantidade) {
        ProdutoModel produto = produtoService.buscarProduto(produtoId);
        int estoqueAtual = produto.getQuantidadeEstoque();

        if (estoqueAtual < quantidade) {
            throw new RuntimeException("Quantidade insuficiente em estoque.");
        }

        produto.setQuantidadeEstoque(estoqueAtual - quantidade);

        EstoqueModel saidaEstoque = new EstoqueModel();
        saidaEstoque.setProduto(produto);
        saidaEstoque.setQuantidade(quantidade);
        saidaEstoque.setTipo("Saida");
        saidaEstoque.setData(LocalDateTime.now());

        produtoService.atualizarProduto(produto);
        return estoqueRepository.save(saidaEstoque);
    }

    public List<EstoqueModel> listarEntradas() {
        return estoqueRepository.findByTipo("Entrada");
    }

    public List<EstoqueModel> listarSaidas() {
        return estoqueRepository.findByTipo("Saida");
    }

}

