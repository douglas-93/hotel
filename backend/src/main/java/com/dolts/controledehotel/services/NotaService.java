package com.dolts.controledehotel.services;

import com.dolts.controledehotel.models.NotaModel;
import com.dolts.controledehotel.repositories.NotaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NotaService {

    @Autowired
    private NotaRepository notaRepository;

    public List<NotaModel> findAll() {
        return notaRepository.findAll();
    }

    public NotaModel findById(Long id) {
        Optional<NotaModel> nota = notaRepository.findById(id);
        return nota.orElse(null);
    }

    public NotaModel insert(NotaModel novaNota) {
        return notaRepository.save(novaNota);
    }

    public List<NotaModel> insertMany(List<NotaModel> novasNotas) {
        return notaRepository.saveAll(novasNotas);
    }

    public void delete(Long id) {
        notaRepository.deleteById(id);
    }

    public NotaModel update(Long id, NotaModel notaAlterada) {
        NotaModel nota = notaRepository.findById(id).orElse(null);
        if (nota != null) {
            nota.setDataEmissao(notaAlterada.getDataEmissao());
            nota.setDiasDeHospedagem(notaAlterada.getDiasDeHospedagem());
            nota.setHospedes(notaAlterada.getHospedes());
            nota.setQuarto(notaAlterada.getQuarto());
            nota.setConsumos(notaAlterada.getConsumos());
            nota.setValor(notaAlterada.getValor());
            return notaRepository.save(nota);
        }
        return null;
    }
}
