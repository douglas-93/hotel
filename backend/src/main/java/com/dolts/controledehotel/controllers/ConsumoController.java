package com.dolts.controledehotel.controllers;

import com.dolts.controledehotel.models.ConsumoModel;
import com.dolts.controledehotel.services.ConsumoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/consumo")
public class ConsumoController {
    @Autowired
    private ConsumoService consumoService;

    @GetMapping
    public ResponseEntity<List<ConsumoModel>> findConsumoByReserva() {
        List<ConsumoModel> consumos = consumoService.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(consumos);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<List<ConsumoModel>> findConsumoByReserva(@PathVariable Long id) {
        List<ConsumoModel> consumos = consumoService.findByReserva(id);
        return ResponseEntity.status(HttpStatus.OK).body(consumos);
    }

    @PostMapping
    public ResponseEntity<ConsumoModel> createConsumo(@RequestBody ConsumoModel consumoModel) {
        ConsumoModel consumo = consumoService.createConsumo(consumoModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(consumo);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteConsumo(@PathVariable Long id) {
        consumoService.removeConsumo(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
