package com.dolts.controledehotel.services;

import com.dolts.controledehotel.models.*;
import com.dolts.controledehotel.repositories.CupomConsumoRepository;
import org.springframework.stereotype.Service;

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

    public CupomConsumoService(CupomConsumoRepository cupomConsumoRepository, ProdutoService produtoService,
                               ReservaService reservaService, ConsumoService consumoService) {
        this.cupomConsumoRepository = cupomConsumoRepository;
        this.produtoService = produtoService;
        this.reservaService = reservaService;
        this.consumoService = consumoService;
    }

    public CupomConsumoModel gerarCupomConsumo(Long reservaId) {
        CupomConsumoModel cupom = new CupomConsumoModel();
//        cupom.setValorDiariaHospede(valorDiariaHospede);
        cupom.setDataEmissao(LocalDateTime.now());

        ReservaModel reserva = this.reservaService.findById(reservaId);

        LocalDateTime dataEntrada = reserva.getDataEntrada().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
        LocalDateTime dataSaida = reserva.getDataSaida().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();

        List<ProdutoConsumidoModel> produtosConsumidos = new ArrayList<>();
        List<ConsumoModel> consumo = this.consumoService.findByReserva(reservaId);

        consumo.forEach(c -> {
            ProdutoModel produto = produtoService.buscarProduto(c.getProdutoId());

            ProdutoConsumidoModel produtoConsumido = new ProdutoConsumidoModel();
            produtoConsumido.setProduto(produto);
            produtoConsumido.setQuantidade(c.getQuantidade());
            produtoConsumido.setValor(produto.getPreco());
            produtoConsumido.setCupomConsumo(cupom);

            produtosConsumidos.add(produtoConsumido);
        });

//        List<LocalDateTime> diasDaDiaria = this.calculaDiarias(dataEntrada, dataSaida);

        cupom.setValorDiariaHospede(reserva.getQuarto().getValor());
        cupom.setDataEntrada(dataEntrada);
        cupom.setDataSaida(dataSaida);

        cupom.setProdutosConsumidos(produtosConsumidos);

        return cupomConsumoRepository.save(cupom);
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
}

