package com.projetoIntegrador.RaitoCorp.catalogo.service;

import java.io.IOException;
import java.util.Base64;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.projetoIntegrador.RaitoCorp.catalogo.dto.ProdutoComEstoqueDTO;
import com.projetoIntegrador.RaitoCorp.catalogo.model.Categoria;
import com.projetoIntegrador.RaitoCorp.catalogo.model.Produto;
import com.projetoIntegrador.RaitoCorp.catalogo.model.ProdutoCategoria;
import com.projetoIntegrador.RaitoCorp.catalogo.model.ProdutoCategoriaId;
import com.projetoIntegrador.RaitoCorp.catalogo.repository.CategoriaRepository;
import com.projetoIntegrador.RaitoCorp.catalogo.repository.ProdutoCategoriaRepository;
import com.projetoIntegrador.RaitoCorp.catalogo.repository.ProdutoRepository;
import com.projetoIntegrador.RaitoCorp.estoque.model.Estoque;
import com.projetoIntegrador.RaitoCorp.estoque.repository.EstoqueRepository;

import jakarta.transaction.Transactional;

@Service
public class ProdutoService {

    private final ProdutoRepository produtoRepository;
    private final CategoriaRepository categoriaRepository;
    private final ProdutoCategoriaRepository produtoCategoriaRepository;
    private final EstoqueRepository estoqueRepository;

    public ProdutoService(ProdutoRepository produtoRepository,
                          CategoriaRepository categoriaRepository,
                          ProdutoCategoriaRepository produtoCategoriaRepository,
                          EstoqueRepository estoqueRepository) {
        this.produtoRepository = produtoRepository;
        this.categoriaRepository = categoriaRepository;
        this.produtoCategoriaRepository = produtoCategoriaRepository;
        this.estoqueRepository = estoqueRepository;
    }

    // =========================================================
    // CRUD BÁSICO DE PRODUTOS
    // =========================================================

    public List<Produto> listarTodos() {
        return produtoRepository.findAll();
    }

    public List<Produto> listarProdutosEmDestaque() {
        return produtoRepository.findAll().stream()
                .filter(Produto::isEmDestaque)
                .collect(Collectors.toList());
    }

    /**
     * Lista todos os produtos com informação de estoque
     */
    public List<ProdutoComEstoqueDTO> listarTodosComEstoque() {
        List<Produto> produtos = produtoRepository.findAll();
        return produtos.stream()
                .map(produto -> {
                    Integer quantidade = estoqueRepository.findByIdProduto(produto.getId())
                            .map(Estoque::getQuantidade)
                            .orElse(0);
                    return new ProdutoComEstoqueDTO(produto, quantidade);
                })
                .collect(Collectors.toList());
    }

    /**
     * Busca produto por ID com informação de estoque
     */
    public Optional<ProdutoComEstoqueDTO> buscarPorIdComEstoque(UUID id) {
        return produtoRepository.findById(id)
                .map(produto -> {
                    Integer quantidade = estoqueRepository.findByIdProduto(produto.getId())
                            .map(Estoque::getQuantidade)
                            .orElse(0);
                    return new ProdutoComEstoqueDTO(produto, quantidade);
                });
    }

    public Optional<Produto> buscarPorId(UUID id) {
        return produtoRepository.findById(id);
    }

    public Produto criarProduto(Produto produto) {
        produto.setId(null);
        produto.setAtivo(true);
        return produtoRepository.save(produto);
    }

    public Produto atualizarProduto(UUID id, Produto atualizado) {
        Produto existente = produtoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado"));
        existente.setNome(atualizado.getNome());
        existente.setDescricao(atualizado.getDescricao());
        existente.setPreco(atualizado.getPreco());
        existente.setAtivo(atualizado.isAtivo());
        existente.setPrecoOriginal(atualizado.getPrecoOriginal());
        existente.setEmDestaque(atualizado.isEmDestaque());
        existente.setNovidade(atualizado.isNovidade());
        existente.setPromocao(atualizado.isPromocao());
        return produtoRepository.save(existente);
    }

    @Transactional
    public Produto atualizarParcial(UUID id, Produto atualizacaoParcial) {
        Produto existente = produtoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado"));

        if (atualizacaoParcial.getNome() != null) {
            existente.setNome(atualizacaoParcial.getNome());
        }
        if (atualizacaoParcial.getDescricao() != null) {
            existente.setDescricao(atualizacaoParcial.getDescricao());
        }
        if (atualizacaoParcial.getPreco() != null) {
            existente.setPreco(atualizacaoParcial.getPreco());
        }
        if (atualizacaoParcial.getPrecoOriginal() != null) {
            existente.setPrecoOriginal(atualizacaoParcial.getPrecoOriginal());
        }
        // Atualiza flags booleanas
        if (atualizacaoParcial.isEmDestaque() != null) {
            existente.setEmDestaque(atualizacaoParcial.isEmDestaque());
        }
        if (atualizacaoParcial.isNovidade() != null) {
            existente.setNovidade(atualizacaoParcial.isNovidade());
        }
        if (atualizacaoParcial.isPromocao() != null) {
            existente.setPromocao(atualizacaoParcial.isPromocao());
        }

        return produtoRepository.save(existente);
    }

    @Transactional
    public void excluirProduto(UUID id) {
        // Verificar se o produto existe
        if (!produtoRepository.existsById(id)) {
            throw new RuntimeException("Produto não encontrado");
        }

        // Remover todas as associações de categorias primeiro
        produtoCategoriaRepository.deleteByProdutoId(id);

        // Agora excluir o produto (isso deve funcionar porque removemos as dependências)
        produtoRepository.deleteById(id);
    }

    // =========================================================
    // RELACIONAMENTO COM CATEGORIAS
    // =========================================================

    @Transactional
    public void associarCategoria(UUID idProduto, UUID idCategoria) {
        Produto produto = produtoRepository.findById(idProduto)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado"));

        Categoria categoria = categoriaRepository.findById(idCategoria)
                .orElseThrow(() -> new RuntimeException("Categoria não encontrada"));

        ProdutoCategoria produtoCategoria = new ProdutoCategoria(produto, categoria);
        produtoCategoriaRepository.save(produtoCategoria);
    }

    @Transactional
    public void associarCategoriaPorNome(UUID idProduto, String nomeCategoria) {
        Produto produto = produtoRepository.findById(idProduto)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado"));

        // Buscar categoria ou criar se não existir
        Categoria categoria = categoriaRepository.findByNome(nomeCategoria)
                .orElseGet(() -> {
                    Categoria novaCategoria = new Categoria();
                    novaCategoria.setNome(nomeCategoria);
                    novaCategoria.setDescricao("Categoria " + nomeCategoria);
                    novaCategoria.setAtivo(true);
                    return categoriaRepository.save(novaCategoria);
                });

        // Remover associações antigas de categoria
        produtoCategoriaRepository.deleteByProdutoId(idProduto);

        // Criar nova associação
        ProdutoCategoria produtoCategoria = new ProdutoCategoria(produto, categoria);
        produtoCategoriaRepository.save(produtoCategoria);
    }

    @Transactional
    public List<String> listarCategoriasDoProduto(UUID idProduto) {
        return produtoCategoriaRepository.findByProdutoId(idProduto)
                .stream()
                .map(pc -> {
                    Categoria categoria = pc.getCategoria();
                    // força inicialização antes do fechamento da sessão
                    return categoria != null ? categoria.getNome() : "Categoria desconhecida";
                })
                .collect(Collectors.toList());
    }


    @Transactional
    public void removerCategoria(UUID idProduto, UUID idCategoria) {
        ProdutoCategoriaId id = new ProdutoCategoriaId(idProduto, idCategoria);
        produtoCategoriaRepository.deleteById(id);
    }

    // =========================================================
    // UPLOAD DE IMAGEM
    // =========================================================

    @Transactional
    public Produto uploadImagem(UUID idProduto, MultipartFile imagem) {
        Produto produto = produtoRepository.findById(idProduto)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado"));

        try {
            System.out.println("Recebendo upload de imagem para produto: " + idProduto);
            System.out.println("Nome do arquivo: " + imagem.getOriginalFilename());
            System.out.println("Tamanho do arquivo: " + imagem.getSize() + " bytes");

            // Converter imagem para Base64
            byte[] imageBytes = imagem.getBytes();
            String base64Image = Base64.getEncoder().encodeToString(imageBytes);

            // Determinar o tipo MIME da imagem
            String contentType = imagem.getContentType();
            if (contentType == null || contentType.isEmpty()) {
                contentType = "image/jpeg"; // fallback padrão
            }

            // Criar data URI com formato: data:image/jpeg;base64,/9j/4AAQ...
            String dataUri = "data:" + contentType + ";base64," + base64Image;

            System.out.println("Imagem convertida para base64 com sucesso");
            System.out.println("Tipo MIME: " + contentType);
            System.out.println("Tamanho do base64: " + dataUri.length() + " caracteres");

            // Salvar data URI no banco de dados
            produto.setImagemUrl(dataUri);

            return produtoRepository.save(produto);

        } catch (IOException e) {
            System.err.println("Erro ao converter imagem para base64:");
            System.err.println("Mensagem: " + e.getMessage());
            e.printStackTrace();
            throw new RuntimeException("Erro ao fazer upload da imagem: " + e.getMessage(), e);
        }
    }
}
