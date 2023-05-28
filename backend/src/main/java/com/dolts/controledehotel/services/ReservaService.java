package com.dolts.controledehotel.services;

import com.dolts.controledehotel.models.ReservaModel;
import com.dolts.controledehotel.repositories.ReservaRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReservaService {
    @Autowired
    private ReservaRepository reservaRepository;

    public List<ReservaModel> findAll() {
        return reservaRepository.findAll();
    }

    public ReservaModel findById(Long id) {
        Optional<ReservaModel> reserva = reservaRepository.findById(id);
        return reserva.get();
    }

    public ReservaModel insert(ReservaModel novaReserva) {
        return reservaRepository.save(novaReserva);
    }

    public void delete(Long id) {
        try {
            reservaRepository.deleteById(id);
        } catch (EmptyResultDataAccessException | DataIntegrityViolationException e) {
            throw new RuntimeException("Entidade não encontrada! ID: " + id);
        }
    }

    public ReservaModel update(Long id, ReservaModel reservaAlterada) {
        try {
            ReservaModel reserva = reservaRepository.getReferenceById(id);
            updateData(reserva, reservaAlterada);
            return reservaRepository.save(reserva);
        } catch (EntityNotFoundException e) {
            throw new RuntimeException("Entidade não encontrada! ID: " + id);
        }
    }

    private void updateData(ReservaModel reserva, ReservaModel reservaAlterada) {
        reserva.setHospedes(reservaAlterada.getHospedes());
        reserva.setDataEntrada(reservaAlterada.getDataEntrada());
        reserva.setDataSaida(reservaAlterada.getDataSaida());
        reserva.setObservacao(reservaAlterada.getObservacao());
        reserva.setQuarto(reservaAlterada.getQuarto());
    }
}
