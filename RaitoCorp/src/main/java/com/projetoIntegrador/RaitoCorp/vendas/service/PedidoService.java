package com.projetoIntegrador.RaitoCorp.vendas.service;

import com.projetoIntegrador.RaitoCorp.vendas.model.*;
import com.projetoIntegrador.RaitoCorp.vendas.repository.*;
import com.projetoIntegrador.RaitoCorp.estoque.service.EstoqueService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.math.BigDecimal;
import java.util.*;

@Service
public class PedidoService {

    private final PedidoRepository pedidoRepository;
    private final ItemPedidoRepository itemPedidoRepository;
    private final CarrinhoService carrinhoService;
    private final EstoqueService estoqueService;

    public PedidoService(
            PedidoRepository pedidoRepository,
            ItemPedidoRepository itemPedidoRepository,
            CarrinhoService carrinhoService,
            EstoqueService estoqueService
    ) {
        this.pedidoRepository = pedidoRepository;
        this.itemPedidoRepository = itemPedidoRepository;
        this.carrinhoService = carrinhoService;
        this.estoqueService = estoqueService;
    }

    public List<Pedido> listarPorCliente(UUID idCliente) {
        return pedidoRepository.findByIdCliente(idCliente);
    }

    @Transactional
    public Pedido finalizarPedido(UUID idCliente, UUID idCarrinho, UUID idEnderecoEntrega) {
        BigDecimal total = carrinhoService.calcularTotal(idCarrinho);
        Pedido pedido = new Pedido();
        pedido.setIdCliente(idCliente);
        pedido.setIdEnderecoEntrega(idEnderecoEntrega);
        pedido.setValorTotal(total);
        pedido.setStatus("PENDENTE");
        pedido = pedidoRepository.save(pedido);

        // Itens do carrinho
        List<ItemCarrinho> itensCarrinho = carrinhoService.listarItens(idCarrinho);
        for (ItemCarrinho item : itensCarrinho) {
            ItemPedido novo = new ItemPedido();
            novo.setIdPedido(pedido.getIdPedido());
            novo.setIdProduto(item.getIdProduto());
            novo.setQuantidade(item.getQuantidade());
            novo.setPrecoUnitario(item.getPrecoUnitario());
            itemPedidoRepository.save(novo);

            // Dá baixa no estoque
            estoqueService.movimentarSaida(item.getIdProduto(), item.getQuantidade());
        }

        // Limpa o carrinho
        carrinhoService.limparCarrinho(idCarrinho);

        return pedido;
    }

    public Optional<Pedido> buscarPorId(UUID idPedido) {
        return pedidoRepository.findById(idPedido);
    }

    public Pedido atualizarStatus(UUID idPedido, String novoStatus) {
        Pedido pedido = pedidoRepository.findById(idPedido)
                .orElseThrow(() -> new RuntimeException("Pedido não encontrado"));
        pedido.setStatus(novoStatus);
        return pedidoRepository.save(pedido);
    }
}
