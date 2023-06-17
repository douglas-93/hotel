package com.dolts.controledehotel.controllers;

import com.dolts.controledehotel.models.ConsumoModel;
import com.dolts.controledehotel.services.ConsumoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/consumos")
public class ConsumoController {

    @Autowired
    private ConsumoService consumoService;

    @GetMapping
    public ResponseEntity<List<ConsumoModel>> findAll() {
        List<ConsumoModel> consumos = consumoService.findAll();
        return ResponseEntity.ok(consumos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ConsumoModel> findById(@PathVariable Long id) {
        ConsumoModel consumo = consumoService.findById(id);
        if (consumo != null) {
            return ResponseEntity.ok(consumo);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<ConsumoModel> insert(@RequestBody ConsumoModel novoConsumo) {
        ConsumoModel consumo = consumoService.insert(novoConsumo);
        URI location = URI.create("/consumos/" + consumo.getId());
        return ResponseEntity.created(location).body(consumo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ConsumoModel> update(@PathVariable Long id, @RequestBody ConsumoModel consumoAlterado) {
        ConsumoModel consumoAtualizado = consumoService.update(id, consumoAlterado);
        if (consumoAtualizado != null) {
            return ResponseEntity.ok(consumoAtualizado);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        consumoService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
