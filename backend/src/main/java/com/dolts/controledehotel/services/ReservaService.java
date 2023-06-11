package com.dolts.controledehotel.services;

import com.dolts.controledehotel.models.QuartoModel;
import com.dolts.controledehotel.models.ReservaModel;
import com.dolts.controledehotel.repositories.ReservaRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ReservaService {
    @Autowired
    private ReservaRepository reservaRepository;

    public List<ReservaModel> findAll() {
        Sort sortById = Sort.by(Sort.Direction.DESC, "id");
        return reservaRepository.findAll(sortById);
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

    @Transactional
    public List<ReservaModel> findByQuartoAndData(QuartoModel quarto, Date dataEntrada, Date dataSaida) {
//        Sort sortEntrada = Sort.by(Sort.Direction.ASC, "dataEntrada");
        return reservaRepository.findByDateAndQuartoId(dataEntrada, dataSaida, quarto.getId());
    }

    public void fazerCheckIn(Long reservaId) {

        ReservaModel reserva = reservaRepository.findById(reservaId).orElseThrow(() -> new RuntimeException("Reserva não encontrada"));

        if (!reserva.isCheckedIn()) {
            reserva.setCheckIn(true);
            reservaRepository.save(reserva);
        } else {
            throw new RuntimeException("Reserva já fez check-in");
        }
    }

    public void fazerCheckOut(Long reservaId) {
        ReservaModel reserva = reservaRepository.findById(reservaId).orElseThrow(() -> new RuntimeException("Reserva não encontrada"));

        if (reserva.isCheckedIn() && !reserva.isCheckedOut()) {
            reserva.setCheckOut(true);
            reservaRepository.save(reserva);
        } else {
            throw new RuntimeException("Reserva não fez check-in ou já fez check-out");
        }
    }

    public void cancelarReserva(Long reservaId, String motivo) {
        ReservaModel reserva = reservaRepository.findById(reservaId).orElseThrow(() -> new RuntimeException("Reserva não encontrada"));
        if (!reserva.isCancelada()) {
            reserva.setCancelada(true);
            reserva.setMotivoCancelamento(motivo);
            reservaRepository.save(reserva);
        } else {
            throw new RuntimeException("Reserva já cancelada.");
        }
    }

}
