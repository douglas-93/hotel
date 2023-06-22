package com.dolts.controledehotel.controllers;

import com.dolts.controledehotel.models.ProdutoModel;
import com.dolts.controledehotel.models.SaidaModel;
import com.dolts.controledehotel.services.ProdutoService;
import com.dolts.controledehotel.services.SaidaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping(value = "/saidas")
public class SaidaController {
    @Autowired
    private SaidaService saidaService;
    @Autowired
    private ProdutoService produtoService;

    @GetMapping
    public ResponseEntity<List<SaidaModel>> listarSaidas() {
        List<SaidaModel> saidas = saidaService.listarSaidas();
        return new ResponseEntity<>(saidas, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<SaidaModel> buscarSaidaPorId(@PathVariable Long id) {
        Optional<SaidaModel> saida = saidaService.buscarSaidaPorId(id);
        return saida.map(saidaModel -> new ResponseEntity<>(saidaModel, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(null, HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<SaidaModel> salvarSaida(@RequestBody SaidaModel saida) {
        ProdutoModel produto = saida.getProduto();
        saida.setProduto(null);
        Optional<ProdutoModel> p = produtoService.buscarProdutoPorId(produto.getId());
        p.ifPresent(saida::setProduto);

        SaidaModel novaSaida = saidaService.salvarSaida(saida);
        if (novaSaida != null) {
            ProdutoModel prodAtualizado = p.get();
            prodAtualizado.setQuantidade(prodAtualizado.getQuantidade() - Math.abs(novaSaida.getQuantidade()));
            produtoService.atualizarProduto(prodAtualizado);
            return new ResponseEntity<>(novaSaida, HttpStatus.CREATED);
        }
        return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removerSaida(@PathVariable Long id) {
        saidaService.removerSaida(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
