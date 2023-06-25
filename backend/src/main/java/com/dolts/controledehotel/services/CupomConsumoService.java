package com.dolts.controledehotel.services;

import com.dolts.controledehotel.models.CupomConsumoModel;
import com.dolts.controledehotel.models.ProdutoConsumidoModel;
import com.dolts.controledehotel.models.ProdutoModel;
import com.dolts.controledehotel.repositories.CupomConsumoRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class CupomConsumoService {
    private final CupomConsumoRepository cupomConsumoRepository;
    private final ProdutoService produtoService;
    private final EstoqueService estoqueService;

    public CupomConsumoService(CupomConsumoRepository cupomConsumoRepository, ProdutoService produtoService, EstoqueService estoqueService) {
        this.cupomConsumoRepository = cupomConsumoRepository;
        this.produtoService = produtoService;
        this.estoqueService = estoqueService;
    }

    public CupomConsumoModel gerarCupomConsumo(double valorDiariaHospede, List<Long> produtosIds, List<Integer> quantidades) {
        CupomConsumoModel cupom = new CupomConsumoModel();
        cupom.setValorDiariaHospede(valorDiariaHospede);
        cupom.setDataValidade(LocalDateTime.now().plusDays(7));

        List<ProdutoConsumidoModel> produtosConsumidos = new ArrayList<>();

        for (int i = 0; i < produtosIds.size(); i++) {
            Long produtoId = produtosIds.get(i);
            int quantidade = quantidades.get(i);

            ProdutoModel produto = produtoService.buscarProduto(produtoId);

            // Registra a saída no estoque
            estoqueService.darSaidaEstoque(produtoId, quantidade);

            ProdutoConsumidoModel produtoConsumido = new ProdutoConsumidoModel();
            produtoConsumido.setProduto(produto);
            produtoConsumido.setQuantidade(quantidade);
            produtoConsumido.setCupomConsumo(cupom);

            produtosConsumidos.add(produtoConsumido);
        }

        cupom.setProdutosConsumidos(produtosConsumidos);

        return cupomConsumoRepository.save(cupom);
    }

}

