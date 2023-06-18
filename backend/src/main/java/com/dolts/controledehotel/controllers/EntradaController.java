package com.dolts.controledehotel.controllers;

import com.dolts.controledehotel.models.EntradaModel;
import com.dolts.controledehotel.models.ProdutoModel;
import com.dolts.controledehotel.services.EntradaService;
import com.dolts.controledehotel.services.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping(value = "/entradas")
public class EntradaController {
    @Autowired
    private EntradaService entradaService;
    @Autowired
    private ProdutoService produtoService;

    @GetMapping
    public ResponseEntity<List<EntradaModel>> listarEntradas() {
        List<EntradaModel> entradas = entradaService.listarEntradas();
        return new ResponseEntity<>(entradas, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<EntradaModel> buscarEntradaPorId(@PathVariable Long id) {
        Optional<EntradaModel> entrada = entradaService.buscarEntradaPorId(id);
        return entrada.map(entradaModel -> new ResponseEntity<>(entradaModel, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(null, HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<EntradaModel> salvarEntrada(@RequestBody EntradaModel entrada) {
        ProdutoModel produto = entrada.getProduto();
        entrada.setProduto(null);
        Optional<ProdutoModel> p = produtoService.buscarProdutoPorId(produto.getId());
        p.ifPresent(entrada::setProduto);

        EntradaModel novaEntrada = entradaService.salvarEntrada(entrada);
        if (novaEntrada != null) {
            ProdutoModel prodAtualizado = p.get();
            prodAtualizado.setQuantidade(prodAtualizado.getQuantidade() + novaEntrada.getQuantidade());
            produtoService.atualizarProduto(prodAtualizado);
            return new ResponseEntity<>(novaEntrada, HttpStatus.CREATED);
        }
        return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removerEntrada(@PathVariable Long id) {
        entradaService.removerEntrada(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
