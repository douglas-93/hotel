package com.dolts.controledehotel.controllers;

import com.dolts.controledehotel.models.QuartoModel;
import com.dolts.controledehotel.services.QuartoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping(value = "/quartos")
public class QuartoController {
    @Autowired
    private QuartoService quartoService;

    @GetMapping
    public ResponseEntity<List<QuartoModel>> findAll() {
        List<QuartoModel> quartos = quartoService.findAll();
        return ResponseEntity.ok().body(quartos);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<QuartoModel> findById(@PathVariable Long id) {
        QuartoModel quarto = quartoService.findById(id);
        return ResponseEntity.ok().body(quarto);
    }

    @PostMapping
    public ResponseEntity<QuartoModel> insert(@RequestBody QuartoModel novoQuarto) {
        novoQuarto = quartoService.insert(novoQuarto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(novoQuarto.getId()).toUri();
        return ResponseEntity.created(uri).body(novoQuarto);
    }

    @PostMapping(value = "/m")
    public ResponseEntity<List<QuartoModel>> insertMany(@RequestBody List<QuartoModel> novosQuartos) {
        novosQuartos = quartoService.insertMany(novosQuartos);
        return ResponseEntity.ok().body(novosQuartos);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<QuartoModel> update(@PathVariable Long id, @RequestBody QuartoModel quartoAlterado) {
        quartoAlterado = quartoService.update(id, quartoAlterado);
        return ResponseEntity.ok().body(quartoAlterado);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        quartoService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
