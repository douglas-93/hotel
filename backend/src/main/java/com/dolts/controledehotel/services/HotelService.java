package com.dolts.controledehotel.services;

import com.dolts.controledehotel.models.HotelModel;
import com.dolts.controledehotel.repositories.HotelRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HotelService {
    @Autowired
    private HotelRepository hotelRepository;

    public List<HotelModel> findAll() {
        return hotelRepository.findAll();
    }

    public HotelModel findById(Integer id) {
        Optional<HotelModel> hotel = hotelRepository.findById(id);
        return hotel.get();
    }

    public HotelModel insert(HotelModel novoHotel) {
        return hotelRepository.save(novoHotel);
    }

    public void delete(Integer id) {
        try {
            hotelRepository.deleteById(id);
        } catch (EmptyResultDataAccessException | DataIntegrityViolationException e) {
            throw new RuntimeException("Entidade não encontrada! ID: " + id);
        }
    }

    public HotelModel update(Integer id, HotelModel hotelAlterado) {
        try {
            HotelModel hotel = hotelRepository.getReferenceById(id);
            updateData(hotel, hotelAlterado);
            return hotelRepository.save(hotel);
        } catch (EntityNotFoundException e) {
            throw new RuntimeException("Entidade não encontrada! ID: " + id);
        }
    }

    private void updateData(HotelModel hotel, HotelModel hotelAlterado) {
        hotel.setNome(hotelAlterado.getNome());
        hotel.setCnpj(hotelAlterado.getCnpj());
        hotel.setCidade(hotelAlterado.getCidade());
        hotel.setEstado(hotelAlterado.getEstado());
        hotel.setLogradouro(hotelAlterado.getLogradouro());
        hotel.setNumero(hotelAlterado.getNumero());
        hotel.setPais(hotelAlterado.getPais());
    }
}
