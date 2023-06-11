package com.dolts.controledehotel.controllers;

import com.dolts.controledehotel.models.HospedeModel;
import com.dolts.controledehotel.repositories.HospedeRepository;
import com.dolts.controledehotel.services.HospedeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping(value = "hospedes")
public class HospedeController {
    @Autowired
    private HospedeService hospedeService;

    @Autowired
    private HospedeRepository hr;

    @GetMapping
    public ResponseEntity<List<HospedeModel>> findAll() {
        List<HospedeModel> hospedes = hospedeService.findAll();
        return ResponseEntity.ok().body(hospedes);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<HospedeModel> findById(@PathVariable Long id) {
        HospedeModel hospede = hospedeService.findById(id);
        return ResponseEntity.ok().body(hospede);
    }

    @GetMapping(value = "/data")
    public ResponseEntity<List<HospedeModel>> findByNomeOrCpf(@RequestParam("nome") Optional<String> nome,
                                                              @RequestParam("cpf") Optional<String> cpf) {
        if (nome.isPresent() && cpf.isPresent()) {
            return ResponseEntity.ok().body(hospedeService.findByNomeOrCpf(nome.get(), cpf.get()));
        } else if (nome.isPresent()) {
            return ResponseEntity.ok().body(hospedeService.findByNome(nome.get()));
        } else if (cpf.isPresent()) {
            return ResponseEntity.ok().body(hospedeService.findByCpf(cpf.get()));
        }
        return ResponseEntity.ok().body(hospedeService.findAll());
    }

    @PostMapping
    public ResponseEntity<HospedeModel> insert(@RequestBody HospedeModel novoHospede) {
        novoHospede = hospedeService.insert(novoHospede);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(novoHospede.getId()).toUri();
        return ResponseEntity.created(uri).body(novoHospede);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        hospedeService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<HospedeModel> update(@PathVariable Long id, @RequestBody HospedeModel hospedeAlterado) {
        hospedeAlterado = hospedeService.update(id, hospedeAlterado);
        return ResponseEntity.ok().body(hospedeAlterado);
    }
}
