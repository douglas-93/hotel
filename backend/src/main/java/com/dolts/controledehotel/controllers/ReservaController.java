package com.dolts.controledehotel.controllers;

import com.dolts.controledehotel.models.QuartoModel;
import com.dolts.controledehotel.models.ReservaModel;
import com.dolts.controledehotel.services.ReservaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
            List<String> datasReservadas = new ArrayList<>();
            reservas.forEach(reserva -> datasReservadas.add("Reserva: " + reserva.getId() +
                    " Quarto: " + reserva.getQuarto().getNome() + " Entrada: " + formatter.format(reserva.getDataEntrada()) +
                    " Saida: " + formatter.format(reserva.getDataSaida())));
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Quarto ocupado nesse(s) dia(s): " + datasReservadas);
        }

        novaReserva = reservaService.insert(novaReserva);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(novaReserva.getId()).toUri();
        return ResponseEntity.created(uri).body(novaReserva);
    }

    @PostMapping(value = "/{id}/checkin", consumes = "application/json", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> fazerCheckIn(@PathVariable("id") Long reservaId,
                                             @RequestPart("observacao") Optional<String> observacao,
                                             @RequestPart("dataSaida") Optional<Date> dataSaida) {
        try {
            System.out.println(reservaId);
            if (observacao.isPresent())
                System.out.println(observacao.get());
            if (dataSaida.isPresent())
                System.out.println(dataSaida.get());


            reservaService.fazerCheckIn(reservaId, observacao, dataSaida);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            if (e.getMessage().contains("não encontrada")) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Reserva não encontrada");
            }
            if (e.getMessage().contains("fez check-in")) {
                throw new ResponseStatusException(HttpStatus.CONFLICT, "Check-in já realizado");
            }
        }
        return ResponseEntity.badRequest().build();
    }

    @PostMapping("/{id}/checkout")
    public ResponseEntity<Void> fazerCheckOut(@PathVariable("id") Long reservaId) {
        try {
            reservaService.fazerCheckOut(reservaId);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            if (e.getMessage().contains("não encontrada")) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Reserva não encontrada");
            }
            if (e.getMessage().contains("fez check-in")) {
                throw new ResponseStatusException(HttpStatus.CONFLICT, "Reserva não fez check-in ou já fez check-out");
            }
        }
        return ResponseEntity.badRequest().build();
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
