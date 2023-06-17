package com.dolts.controledehotel.controllers;

import com.dolts.controledehotel.models.ProdutoEntradaModel;
import com.dolts.controledehotel.services.ProdutoEntradaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/produtos-entrada")
public class ProdutoEntradaController {

    private final ProdutoEntradaService produtoEntradaService;

    @Autowired
    public ProdutoEntradaController(ProdutoEntradaService produtoEntradaService) {
        this.produtoEntradaService = produtoEntradaService;
    }

    @PostMapping
    public ResponseEntity<ProdutoEntradaModel> salvarProdutoEntrada(@RequestBody ProdutoEntradaModel produtoEntrada) {
        ProdutoEntradaModel produtoEntradaSalvo = produtoEntradaService.salvarProdutoEntrada(produtoEntrada);
        return new ResponseEntity<>(produtoEntradaSalvo, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProdutoEntradaModel> buscarProdutoEntradaPorId(@PathVariable Long id) {
        return produtoEntradaService.buscarProdutoEntradaPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<ProdutoEntradaModel>> buscarTodosProdutosEntrada() {
        List<ProdutoEntradaModel> produtosEntrada = produtoEntradaService.buscarTodosProdutosEntrada();
        return new ResponseEntity<>(produtosEntrada, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removerProdutoEntrada(@PathVariable Long id) {
        produtoEntradaService.removerProdutoEntrada(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
