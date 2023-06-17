package com.dolts.controledehotel.controllers;

import com.dolts.controledehotel.models.EstoqueModel;
import com.dolts.controledehotel.services.EstoqueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/estoques")
public class EstoqueController {

    private final EstoqueService estoqueService;

    @Autowired
    public EstoqueController(EstoqueService estoqueService) {
        this.estoqueService = estoqueService;
    }

    @PostMapping
    public ResponseEntity<EstoqueModel> salvarEstoque(@RequestBody EstoqueModel estoque) {
        EstoqueModel estoqueSalvo = estoqueService.salvarEstoque(estoque);
        return new ResponseEntity<>(estoqueSalvo, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EstoqueModel> buscarEstoquePorId(@PathVariable Long id) {
        return estoqueService.buscarEstoquePorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<EstoqueModel>> buscarTodosEstoques() {
        List<EstoqueModel> estoques = estoqueService.buscarTodosEstoques();
        return new ResponseEntity<>(estoques, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removerEstoque(@PathVariable Long id) {
        estoqueService.removerEstoque(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
