package com.dolts.controledehotel.controllers;

import com.dolts.controledehotel.models.QuartoModel;
import com.dolts.controledehotel.models.ReservaModel;
import com.dolts.controledehotel.services.ReservaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping(value = "/reservas")
public class ReservaController {
    @Autowired
    private ReservaService reservaService;

    @GetMapping
    public ResponseEntity<List<ReservaModel>> findAll() {
        List<ReservaModel> reservas = reservaService.findAll();
        return ResponseEntity.ok().body(reservas);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<ReservaModel> findById(@PathVariable Long id) {
        ReservaModel reserva = reservaService.findById(id);
        return ResponseEntity.ok().body(reserva);
    }

    @PostMapping
    public ResponseEntity<ReservaModel> insert(@RequestBody ReservaModel novaReserva) {

        QuartoModel quarto = novaReserva.getQuarto();
        Date entrada = novaReserva.getDataEntrada();
        Date saida = novaReserva.getDataSaida();

        List<ReservaModel> reservas = reservaService.findByQuartoAndData(quarto, entrada, saida);

        if (!reservas.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Quarto ocupado nesse dia");
        }

        novaReserva = reservaService.insert(novaReserva);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(novaReserva.getId()).toUri();
        return ResponseEntity.created(uri).body(novaReserva);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<ReservaModel> update(@PathVariable Long id, @RequestBody ReservaModel reservaAlterado) {
        reservaAlterado = reservaService.update(id, reservaAlterado);
        return ResponseEntity.ok().body(reservaAlterado);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        reservaService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
