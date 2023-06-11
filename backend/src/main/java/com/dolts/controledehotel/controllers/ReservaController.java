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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

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
            reservas.forEach(reserva -> datasReservadas.add("Reserva: " + reserva.getId() + " Quarto: " + reserva.getQuarto().getNome() + " Entrada: " + formatter.format(reserva.getDataEntrada()) + " Saida: " + formatter.format(reserva.getDataSaida())));
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Quarto ocupado nesse(s) dia(s): " + datasReservadas);
        }

        novaReserva = reservaService.insert(novaReserva);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(novaReserva.getId()).toUri();
        return ResponseEntity.created(uri).body(novaReserva);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping(value = "/{id}/checkin")
    public ResponseEntity<Void> fazerCheckIn(@PathVariable("id") Long reservaId, @RequestParam("observacao") Optional<String> observacao, @RequestParam("dataSaida") Optional<String> dataSaida) throws ParseException {

        ReservaModel reserva = reservaService.findById(reservaId);
        QuartoModel quarto = reserva.getQuarto();
        Date entrada = reserva.getDataEntrada();

        if (dataSaida.isPresent()) {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date saida = dateFormat.parse(dataSaida.get());

            if (reserva.getDataSaida().compareTo(saida) != 0) {
                reserva.setDataSaida(saida);
            }
        }

        observacao.ifPresent(reserva::setObservacao);

        List<ReservaModel> reservas = reservaService.findByQuartoAndData(quarto, entrada, reserva.getDataSaida());

        if (!reservas.isEmpty() && reservas.size() > 1) {
            reservas.forEach(r -> {
                if (!Objects.equals(r.getId(), reserva.getId()) && Objects.equals(r.getQuarto().getId(), reserva.getQuarto().getId())) {
                    throw new ResponseStatusException(HttpStatus.CONFLICT, "Quarto ocupado neste dia, verifique a data de saída, ou mantenha a original");
                }
            });
        }

        try {
            reservaService.fazerCheckIn(reservaId);
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

    @PostMapping("/{id}/cancelar")
    public ResponseEntity<Void> cancelarReserva(@PathVariable("id") Long reservaId, @RequestParam("motivo") String motivo) {
        if (motivo.isEmpty() || motivo.isBlank()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Motivo não informado");
        }

        try {
            reservaService.cancelarReserva(reservaId, motivo);
            return ResponseEntity.badRequest().build();
        } catch (Exception e) {
            return ResponseEntity.ok().build();
        }
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
