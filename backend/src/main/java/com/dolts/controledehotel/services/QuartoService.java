package com.dolts.controledehotel.services;

import com.dolts.controledehotel.enumerators.CategoriasEnum;
import com.dolts.controledehotel.enumerators.TiposEnum;
import com.dolts.controledehotel.models.QuartoModel;
import com.dolts.controledehotel.repositories.QuartoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class QuartoService {

    @Autowired
    private QuartoRepository quartoRepository;

    public List<QuartoModel> findAll() {
        Sort sortById = Sort.by(Sort.Direction.ASC, "id");
        return quartoRepository.findAll(sortById);
    }

    public QuartoModel findById(Long id) {
        Optional<QuartoModel> quartos = quartoRepository.findById(id);
        return quartos.get();
    }

    public QuartoModel insert(QuartoModel novoQuarto) {
        byte[] imagem = novoQuarto.getImagem();
        List<String> itens = new ArrayList<>(novoQuarto.getItens());

        novoQuarto.setImagem(null); // Limpa o atributo imagem para evitar problemas na serialização
        novoQuarto.setItens(null); // Limpa o atributo itens para evitar problemas na serialização

        QuartoModel quartoSalvo = quartoRepository.save(novoQuarto);

        if (imagem != null) {
            quartoSalvo.setImagem(imagem);
            quartoRepository.save(quartoSalvo);
        }

        if (itens != null) {
            quartoSalvo.setItens(itens);
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

        byte[] imagem = quartoAlterado.getImagem();
        List<String> itens = new ArrayList<>(quartoAlterado.getItens());

        quartoAlterado.setImagem(null); // Limpa o atributo imagem para evitar problemas na serialização
        quartoAlterado.setItens(null); // Limpa o atributo itens para evitar problemas na serialização

        QuartoModel quarto = quartoRepository.getReferenceById(id);
        updateData(quarto, quartoAlterado);
        QuartoModel quartoSalvo = quartoRepository.save(quarto);

        if (imagem != null) {
            quartoSalvo.setImagem(imagem);
            quartoRepository.save(quartoSalvo);
        }

        if (itens != null) {
            quartoSalvo.setItens(itens);
            quartoRepository.save(quartoSalvo);
        }

        return quartoSalvo;
    }

    private void updateData(QuartoModel quarto, QuartoModel quartoAlterado) {
        quarto.setNome(quartoAlterado.getNome());
        quarto.setCategoria(quartoAlterado.getCategoria());
        quarto.setTipo(quartoAlterado.getTipo());
        quarto.setAtivo(quartoAlterado.isAtivo());
        quarto.setValor(quartoAlterado.getValor());
        quarto.setItens(quartoAlterado.getItens());
    }

    @Transactional
    public List<QuartoModel> findByNome(String nome) {
        return quartoRepository.findByNomeContainsIgnoreCase(nome);
    }

    @Transactional
    public List<QuartoModel> findByAtivo(Boolean ativo) {
        return quartoRepository.findByAtivo(ativo);
    }

    @Transactional
    public List<QuartoModel> findByTipo(TiposEnum tipo) {
        return quartoRepository.findByTipo(tipo);
    }

    @Transactional
    public List<QuartoModel> findByCategoria(CategoriasEnum categoria) {
        return quartoRepository.findByCategoria(categoria);
    }
}
