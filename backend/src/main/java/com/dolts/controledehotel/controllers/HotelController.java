package com.dolts.controledehotel.controllers;

import com.dolts.controledehotel.models.HotelModel;
import com.dolts.controledehotel.services.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping(value = "/hoteis")
public class HotelController {
    @Autowired
    private HotelService hotelService;

    @GetMapping
    public ResponseEntity<List<HotelModel>> findAll() {
        List<HotelModel> hoteis = hotelService.findAll();
        return ResponseEntity.ok().body(hoteis);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<HotelModel> findById(@PathVariable Integer id) {
        HotelModel hotel = hotelService.findById(id);
        return ResponseEntity.ok().body(hotel);
    }

    @PostMapping
    public ResponseEntity<HotelModel> insert(@RequestBody HotelModel novoHotel) {
        novoHotel = hotelService.insert(novoHotel);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(novoHotel.getId()).toUri();
        return ResponseEntity.created(uri).body(novoHotel);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        hotelService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<HotelModel> update(@PathVariable Integer id, @RequestBody HotelModel hotelAlterado) {
        hotelAlterado = hotelService.update(id, hotelAlterado);
        return ResponseEntity.ok().body(hotelAlterado);
    }
}
