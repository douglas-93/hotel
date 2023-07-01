package com.dolts.controledehotel.controllers;

import com.dolts.controledehotel.dtos.MovimentacaoEstoqueRequest;
import com.dolts.controledehotel.models.EstoqueModel;
import com.dolts.controledehotel.services.EstoqueService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/estoque")
public class EstoqueController {
    private final EstoqueService estoqueService;

    public EstoqueController(EstoqueService estoqueService) {
        this.estoqueService = estoqueService;
    }

    @PostMapping("/entrada")
    public ResponseEntity<EstoqueModel> darEntradaEstoque(@RequestBody MovimentacaoEstoqueRequest movimentacaoEstoqueRequest) {
        Long produtoId = movimentacaoEstoqueRequest.getProdutoId();
        int quantidade = movimentacaoEstoqueRequest.getQuantidade();

        EstoqueModel entradaEstoque = estoqueService.darEntradaEstoque(produtoId, quantidade);
        return ResponseEntity.status(HttpStatus.CREATED).body(entradaEstoque);
    }

    @GetMapping("/entradas")
    public ResponseEntity<List<EstoqueModel>> listarEntradas() {
        List<EstoqueModel> entradas = estoqueService.listarEntradas();
        return ResponseEntity.status(HttpStatus.OK).body(entradas);
    }

    @GetMapping("/saidas")
    public ResponseEntity<List<EstoqueModel>> listarSaidas() {
        List<EstoqueModel> saidas = estoqueService.listarSaidas();
        return ResponseEntity.status(HttpStatus.OK).body(saidas);
    }

    @PostMapping("/saida")
    public ResponseEntity<EstoqueModel> darSaidaEstoque(@RequestBody MovimentacaoEstoqueRequest movimentacaoEstoqueRequest) {
        Long produtoId = movimentacaoEstoqueRequest.getProdutoId();
        int quantidade = movimentacaoEstoqueRequest.getQuantidade();

        EstoqueModel saidaEstoque = estoqueService.darSaidaEstoque(produtoId, quantidade);
        return ResponseEntity.status(HttpStatus.CREATED).body(saidaEstoque);
    }
}
