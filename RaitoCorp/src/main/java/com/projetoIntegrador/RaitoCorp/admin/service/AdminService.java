package com.projetoIntegrador.RaitoCorp.admin.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projetoIntegrador.RaitoCorp.cadastro.model.Usuario;
import com.projetoIntegrador.RaitoCorp.cadastro.repository.UsuarioRepository;
import com.projetoIntegrador.RaitoCorp.cadastro.service.CredencialService;
import com.projetoIntegrador.RaitoCorp.catalogo.dto.ProdutoAdminDTO;
import com.projetoIntegrador.RaitoCorp.catalogo.model.Produto;
import com.projetoIntegrador.RaitoCorp.catalogo.repository.ProdutoRepository;
import com.projetoIntegrador.RaitoCorp.catalogo.service.ProdutoService;
import com.projetoIntegrador.RaitoCorp.estoque.model.Estoque;
import com.projetoIntegrador.RaitoCorp.estoque.repository.EstoqueRepository;
import com.projetoIntegrador.RaitoCorp.vendas.dto.PedidoAdminDTO;
import com.projetoIntegrador.RaitoCorp.vendas.model.ItemPedido;
import com.projetoIntegrador.RaitoCorp.vendas.model.Pedido;
import com.projetoIntegrador.RaitoCorp.vendas.repository.ItemPedidoRepository;
import com.projetoIntegrador.RaitoCorp.vendas.repository.PedidoRepository;

@Service
public class AdminService {

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private EstoqueRepository estoqueRepository;

    @Autowired
    private ItemPedidoRepository itemPedidoRepository;

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private CredencialService credencialService;

    @Autowired
    private ProdutoService produtoService;

    public List<ProdutoAdminDTO> listarProdutosComMetricas() {
        List<Produto> produtos = produtoRepository.findAll();

        return produtos.stream().map(produto -> {
            ProdutoAdminDTO dto = new ProdutoAdminDTO();
            dto.setIdProduto(produto.getId().toString());
            dto.setNome(produto.getNome());

            // Buscar categorias do produto
            List<String> categorias = produtoService.listarCategoriasDoProduto(produto.getId());
            dto.setCategoria(categorias.isEmpty() ? "Geral" : categorias.get(0));

            dto.setPreco(produto.getPreco());
            dto.setUrlImagem(produto.getImagemUrl() != null ? produto.getImagemUrl() : "");
            dto.setEmDestaque(produto.isEmDestaque() != null ? produto.isEmDestaque() : false);
            dto.setDescricao(produto.getDescricao());

            // Buscar estoque
            Optional<Estoque> estoqueOpt = estoqueRepository.findByIdProduto(produto.getId());
            dto.setEstoque(estoqueOpt.map(Estoque::getQuantidade).orElse(0));

            // Calcular vendidos e receita
            List<ItemPedido> itensPedido = itemPedidoRepository.findByIdProduto(produto.getId());
            int vendidos = itensPedido.stream()
                .mapToInt(ItemPedido::getQuantidade)
                .sum();
            dto.setVendidos(vendidos);

            BigDecimal receita = itensPedido.stream()
                .map(item -> item.getPrecoUnitario().multiply(BigDecimal.valueOf(item.getQuantidade())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
            dto.setReceita(receita);

            return dto;
        }).collect(Collectors.toList());
    }

    public List<PedidoAdminDTO> listarPedidosComClientes() {
        List<Pedido> pedidos = pedidoRepository.findAll();

        return pedidos.stream().map(pedido -> {
            PedidoAdminDTO dto = new PedidoAdminDTO();
            dto.setIdPedido(pedido.getIdPedido().toString());
            dto.setValorTotal(pedido.getValorTotal());
            dto.setStatus(pedido.getStatus());
            dto.setCriadoEm(pedido.getCriadoEm());

            // Buscar informações do cliente
            Optional<Usuario> usuarioOpt = usuarioRepository.findById(pedido.getIdCliente());
            if (usuarioOpt.isPresent()) {
                Usuario usuario = usuarioOpt.get();
                String nomeCompleto = usuario.getNome();
                if (usuario.getSobrenome() != null) {
                    nomeCompleto += " " + usuario.getSobrenome();
                }
                dto.setNomeCliente(nomeCompleto);

                // Buscar email do usuário
                credencialService.buscarPorIdUsuario(usuario.getIdUsuario()).ifPresent(credencial -> {
                    dto.setEmailCliente(credencial.getEmail());
                });
            } else {
                dto.setNomeCliente("Cliente não encontrado");
                dto.setEmailCliente("");
            }

            // Contar quantidade de itens
            List<ItemPedido> itens = itemPedidoRepository.findByIdPedido(pedido.getIdPedido());
            int quantidadeTotal = itens.stream()
                .mapToInt(ItemPedido::getQuantidade)
                .sum();
            dto.setQuantidadeItens(quantidadeTotal);

            return dto;
        }).collect(Collectors.toList());
    }
}
