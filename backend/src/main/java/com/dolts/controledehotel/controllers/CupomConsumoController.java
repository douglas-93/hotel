package com.dolts.controledehotel.controllers;

import com.dolts.controledehotel.dtos.CupomConsumoRequest;
import com.dolts.controledehotel.models.CupomConsumoModel;
import com.dolts.controledehotel.services.CupomConsumoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/cupons-consumo")
public class CupomConsumoController {
    private final CupomConsumoService cupomConsumoService;

    public CupomConsumoController(CupomConsumoService cupomConsumoService) {
        this.cupomConsumoService = cupomConsumoService;
    }

    @PostMapping("/{reservaId}")
    public ResponseEntity<CupomConsumoModel> gerarCupomConsumo(@PathVariable Long reservaId) {
        CupomConsumoModel cupomConsumo = cupomConsumoService.gerarCupomConsumo(reservaId);
        return ResponseEntity.status(HttpStatus.CREATED).body(cupomConsumo);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CupomConsumoModel> obterCupomConsumo(@PathVariable Long id) {
        CupomConsumoModel cupomConsumo = cupomConsumoService.obterCupomConsumo(id);
        return ResponseEntity.ok(cupomConsumo);
    }

    @GetMapping
    public ResponseEntity<List<CupomConsumoModel>> obterCuponsConsumo() {
        List<CupomConsumoModel> cuponsConsumo = cupomConsumoService.obterCuponsConsumo();
        return ResponseEntity.ok(cuponsConsumo);
    }

}
