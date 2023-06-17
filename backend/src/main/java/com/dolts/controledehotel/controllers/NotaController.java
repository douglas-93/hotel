package com.dolts.controledehotel.controllers;

import com.dolts.controledehotel.models.NotaModel;
import com.dolts.controledehotel.services.NotaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/notas")
public class NotaController {

    @Autowired
    private NotaService notaService;

    @GetMapping
    public ResponseEntity<List<NotaModel>> findAll() {
        List<NotaModel> notas = notaService.findAll();
        return ResponseEntity.ok(notas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<NotaModel> findById(@PathVariable Long id) {
        NotaModel nota = notaService.findById(id);
        if (nota != null) {
            return ResponseEntity.ok(nota);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<NotaModel> insert(@RequestBody NotaModel novaNota) {
        NotaModel nota = notaService.insert(novaNota);
        URI location = URI.create("/notas/" + nota.getId());
        return ResponseEntity.created(location).body(nota);
    }

    @PutMapping("/{id}")
    public ResponseEntity<NotaModel> update(@PathVariable Long id, @RequestBody NotaModel notaAlterada) {
        NotaModel notaAtualizada = notaService.update(id, notaAlterada);
        if (notaAtualizada != null) {
            return ResponseEntity.ok(notaAtualizada);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        notaService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
