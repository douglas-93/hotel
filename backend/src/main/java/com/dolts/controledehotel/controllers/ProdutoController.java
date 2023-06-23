package com.dolts.controledehotel.controllers;

import com.dolts.controledehotel.models.ProdutoModel;
import com.dolts.controledehotel.services.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/produtos")
public class ProdutoController {

    private final ProdutoService produtoService;

    @Autowired
    public ProdutoController(ProdutoService produtoService) {
        this.produtoService = produtoService;
    }

    @GetMapping
    public ResponseEntity<List<ProdutoModel>> listarProdutos() {
        List<ProdutoModel> produtos = produtoService.listarProdutos();
        return new ResponseEntity<>(produtos, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProdutoModel> buscarProdutoPorId(@PathVariable Long id) {
        Optional<ProdutoModel> produto = produtoService.buscarProdutoPorId(id);
        return produto.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<ProdutoModel> salvarProduto(@RequestBody ProdutoModel produto) {
        if (produto.getQuantidade() == null)
            produto.setQuantidade(0d);
        ProdutoModel produtoSalvo = produtoService.salvarProduto(produto);
        return new ResponseEntity<>(produtoSalvo, HttpStatus.CREATED);
    }

    @PostMapping(value = "/m")
    public ResponseEntity<List<ProdutoModel>> salvarProdutos(@RequestBody List<ProdutoModel> produtos) {
        produtos.forEach(produto -> {
            if (produto.getQuantidade() == null)
                produto.setQuantidade(0d);
        });
        List<ProdutoModel> produtosSalvo = produtoService.salvarProdutos(produtos);
        return new ResponseEntity<>(produtosSalvo, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removerProduto(@PathVariable Long id) {
        produtoService.removerProduto(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
