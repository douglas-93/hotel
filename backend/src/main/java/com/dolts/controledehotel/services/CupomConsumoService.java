package com.dolts.controledehotel.services;

import com.dolts.controledehotel.models.*;
import com.dolts.controledehotel.repositories.CupomConsumoRepository;
import com.dolts.controledehotel.repositories.ProdutoConsumidoRepository;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

@Service
public class CupomConsumoService {
    private final CupomConsumoRepository cupomConsumoRepository;
    private final ProdutoService produtoService;
    private final ReservaService reservaService;
    private final ConsumoService consumoService;
    private final ProdutoConsumidoRepository produtoConsumidoRepository;

    public CupomConsumoService(CupomConsumoRepository cupomConsumoRepository, ProdutoService produtoService,
                               ReservaService reservaService, ConsumoService consumoService, ProdutoConsumidoRepository produtoConsumidoRepository) {
        this.cupomConsumoRepository = cupomConsumoRepository;
        this.produtoService = produtoService;
        this.reservaService = reservaService;
        this.consumoService = consumoService;
        this.produtoConsumidoRepository = produtoConsumidoRepository;
    }

    public CupomConsumoModel gerarCupomConsumo(Long reservaId) {
        CupomConsumoModel cupom = new CupomConsumoModel();
        cupom.setDataEmissao(LocalDateTime.now());
        cupom.setReservaId(reservaId);
        CupomConsumoModel finalCupom = cupomConsumoRepository.save(cupom);

        ReservaModel reserva = this.reservaService.findById(reservaId);

        LocalDateTime dataEntrada = Instant.ofEpochMilli(reserva.getDataEntrada().getTime())
                .atZone(ZoneId.systemDefault())
                .toLocalDateTime();
        LocalDateTime dataSaida = Instant.ofEpochMilli(reserva.getDataSaida().getTime())
                .atZone(ZoneId.systemDefault())
                .toLocalDateTime();


        List<ProdutoConsumidoModel> produtosConsumidos = new ArrayList<>();
        List<ConsumoModel> consumo = this.consumoService.findByReserva(reservaId);

        consumo.forEach(c -> {
            ProdutoModel produto = produtoService.buscarProduto(c.getProdutoId());

            ProdutoConsumidoModel produtoConsumido = new ProdutoConsumidoModel();
            produtoConsumido.setProduto(produto);
            produtoConsumido.setQuantidade(c.getQuantidade());
            produtoConsumido.setValor(produto.getPreco());
            produtoConsumido.setCupomConsumo(finalCupom);

            produtosConsumidos.add(produtoConsumido);
            produtoConsumidoRepository.save(produtoConsumido);
        });

//        List<LocalDateTime> diasDaDiaria = this.calculaDiarias(dataEntrada, dataSaida);

        if (dataSaida.isAfter(LocalDateTime.now())) {
            dataSaida = LocalDateTime.now();
        }

        cupom.setValorDiariaHospede(reserva.getQuarto().getValor());
        cupom.setDataEntrada(dataEntrada);
        cupom.setDataSaida(dataSaida);

        cupom.setProdutosConsumidos(produtosConsumidos);
        cupom.setFechado(true);

        reserva.setCheckOut(true);
        reservaService.update(reserva.getId(), reserva);

        return cupomConsumoRepository.save(cupom);
//        return cupom;
    }

    public List<LocalDateTime> calculaDiarias(LocalDateTime entrada, LocalDateTime saida) {
        List<LocalDateTime> dias = new ArrayList<>();
        LocalDateTime diaAtual = entrada;

        while (diaAtual.isBefore(saida) || diaAtual.isEqual(saida)) {
            dias.add(diaAtual);
            diaAtual = diaAtual.plusDays(1);
        }

        return dias;
    }

    public CupomConsumoModel obterCupomConsumo(Long id) {
        return cupomConsumoRepository.getById(id);
    }

    public List<CupomConsumoModel> obterCuponsConsumo() {
        return cupomConsumoRepository.findAll();
    }
}

