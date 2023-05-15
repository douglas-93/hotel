package com.dolts.controledehotel.services;

import com.dolts.controledehotel.models.HospedeModel;
import com.dolts.controledehotel.repositories.HospedeRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

@Service
public class HospedeService {

    @Autowired
    private HospedeRepository hospedeRepository;

    public List<HospedeModel> findAll() {
        return hospedeRepository.findAll();
    }

    public HospedeModel findById(Long id) {
        Optional<HospedeModel> hospede = hospedeRepository.findById(id);
        return hospede.get();
    }

    public HospedeModel insert(HospedeModel novoHospede) {
        return hospedeRepository.save(novoHospede);
    }

    public void delete(@PathVariable Long id) {
        try {
            hospedeRepository.deleteById(id);
        } catch (EmptyResultDataAccessException | DataIntegrityViolationException e) {
            throw new RuntimeException("Entidade não encontrada! ID: " + id);
        }
    }

    public HospedeModel update(Long id, HospedeModel hospedeAlterado) {
        try {
            HospedeModel hospede = hospedeRepository.getReferenceById(id);
            updateData(hospede, hospedeAlterado);
            return hospedeRepository.save(hospede);
        } catch (EntityNotFoundException e) {
            throw new RuntimeException("Entidade não encontrada! ID: " + id);
        }
    }

    private void updateData(HospedeModel hospede, HospedeModel hospedeAlterado) {
        hospede.setNome(hospedeAlterado.getNome());
        hospede.setNascimento(hospedeAlterado.getNascimento());
        hospede.setCpf(hospedeAlterado.getCpf());
        hospede.setNomeMae(hospedeAlterado.getNomeMae());
        hospede.setNomePai(hospedeAlterado.getNomePai());
        hospede.setTelefone(hospedeAlterado.getTelefone());
        hospede.setEmail(hospedeAlterado.getEmail());
        hospede.setCelular(hospedeAlterado.getCelular());
    }
}
