package com.projetoIntegrador.RaitoCorp.catalogo.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.projetoIntegrador.RaitoCorp.catalogo.model.Categoria;
import com.projetoIntegrador.RaitoCorp.catalogo.model.Produto;
import com.projetoIntegrador.RaitoCorp.catalogo.model.ProdutoCategoria;
import com.projetoIntegrador.RaitoCorp.catalogo.model.ProdutoCategoriaId;
import com.projetoIntegrador.RaitoCorp.catalogo.repository.CategoriaRepository;
import com.projetoIntegrador.RaitoCorp.catalogo.repository.ProdutoCategoriaRepository;
import com.projetoIntegrador.RaitoCorp.catalogo.repository.ProdutoRepository;

import jakarta.transaction.Transactional;

@Service
public class ProdutoService {

    @Value("${upload.directory:uploads/produtos}")
    private String uploadDirectory;

    private final ProdutoRepository produtoRepository;
    private final CategoriaRepository categoriaRepository;
    private final ProdutoCategoriaRepository produtoCategoriaRepository;

    public ProdutoService(ProdutoRepository produtoRepository,
                          CategoriaRepository categoriaRepository,
                          ProdutoCategoriaRepository produtoCategoriaRepository) {
        this.produtoRepository = produtoRepository;
        this.categoriaRepository = categoriaRepository;
        this.produtoCategoriaRepository = produtoCategoriaRepository;
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
        // Atualiza emDestaque se fornecido
        existente.setEmDestaque(atualizacaoParcial.isEmDestaque());

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

        Categoria categoria = categoriaRepository.findByNome(nomeCategoria)
                .orElseThrow(() -> new RuntimeException("Categoria não encontrada: " + nomeCategoria));

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
            // Criar diretório se não existir
            Path uploadPath = Paths.get(uploadDirectory);
            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }

            // Gerar nome único para o arquivo
            String originalFilename = imagem.getOriginalFilename();
            String fileExtension = "";
            if (originalFilename != null && originalFilename.contains(".")) {
                fileExtension = originalFilename.substring(originalFilename.lastIndexOf("."));
            }
            String novoNomeArquivo = idProduto + "_" + System.currentTimeMillis() + fileExtension;

            // Salvar arquivo
            Path filePath = uploadPath.resolve(novoNomeArquivo);
            Files.copy(imagem.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

            // Atualizar URL da imagem no produto
            String imagemUrl = "/uploads/produtos/" + novoNomeArquivo;
            produto.setImagemUrl(imagemUrl);

            return produtoRepository.save(produto);

        } catch (IOException e) {
            throw new RuntimeException("Erro ao fazer upload da imagem: " + e.getMessage());
        }
    }
}
