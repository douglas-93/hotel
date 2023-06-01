package com.dolts.controledehotel.services;

import com.dolts.controledehotel.models.QuartoModel;
import com.dolts.controledehotel.repositories.QuartoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class QuartoService {

    @Autowired
    private QuartoRepository quartoRepository;

    public List<QuartoModel> findAll() {
        return quartoRepository.findAll();
    }

    public QuartoModel findById(Long id) {
        Optional<QuartoModel> quartos = quartoRepository.findById(id);
        return quartos.get();
    }

    public QuartoModel insert(QuartoModel novoQuarto) {
        byte[] imagem = novoQuarto.getImagem();
        novoQuarto.setImagem(null); // Limpa o atributo imagem para evitar problemas na serialização

        QuartoModel quartoSalvo = quartoRepository.save(novoQuarto);

        if (imagem != null) {
            quartoSalvo.setImagem(imagem);
            quartoRepository.save(quartoSalvo);
        }

        return quartoSalvo;
    }

    public List<QuartoModel> insertMany(List<QuartoModel> novosQuartos) {
        return quartoRepository.saveAll(novosQuartos);
    }

    public void delete(Long id) {
        try {
            quartoRepository.deleteById(id);
        } catch (EmptyResultDataAccessException | DataIntegrityViolationException e) {
            throw new RuntimeException("Entidade não encontrada! ID: " + id);
        }
    }

    public QuartoModel update(Long id, QuartoModel quartoAlterado) {
        QuartoModel quarto = quartoRepository.getReferenceById(id);
        updateData(quarto, quartoAlterado);
        return quartoRepository.save(quarto);
    }

    private void updateData(QuartoModel quarto, QuartoModel quartoAlterado) {
        quarto.setNome(quartoAlterado.getNome());
        quarto.setCategoria(quartoAlterado.getCategoria());
        quarto.setTipo(quartoAlterado.getTipo());
        quarto.setAtivo(quartoAlterado.isAtivo());
        quarto.setImagem(quarto.getImagem());
        quarto.setImagemURL(quarto.getImagemURL());
    }
}
