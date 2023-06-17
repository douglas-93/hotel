package com.dolts.controledehotel.controllers;

import com.dolts.controledehotel.models.EntradaModel;
import com.dolts.controledehotel.services.EntradaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/entradas")
public class EntradaController {

    @Autowired
    private EntradaService entradaService;

    @GetMapping
    public ResponseEntity<List<EntradaModel>> findAll() {
        List<EntradaModel> entradas = entradaService.findAll();
        return ResponseEntity.ok(entradas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EntradaModel> findById(@PathVariable Long id) {
        EntradaModel entrada = entradaService.findById(id);
        if (entrada != null) {
            return ResponseEntity.ok(entrada);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<EntradaModel> insert(@RequestBody EntradaModel novaEntrada) {
        EntradaModel entrada = entradaService.insert(novaEntrada);
        URI location = URI.create("/entradas/" + entrada.getId());
        return ResponseEntity.created(location).body(entrada);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EntradaModel> update(@PathVariable Long id, @RequestBody EntradaModel entradaAlterada) {
        EntradaModel entradaAtualizada = entradaService.update(id, entradaAlterada);
        if (entradaAtualizada != null) {
            return ResponseEntity.ok(entradaAtualizada);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        entradaService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
