package com.projetoIntegrador.RaitoCorp.catalogo.service;

import com.projetoIntegrador.RaitoCorp.catalogo.model.ImagemProduto;
import com.projetoIntegrador.RaitoCorp.catalogo.model.Produto;
import com.projetoIntegrador.RaitoCorp.catalogo.repository.ImagemProdutoRepository;
import com.projetoIntegrador.RaitoCorp.catalogo.repository.ProdutoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ImagemProdutoService {

    private final ImagemProdutoRepository imagemProdutoRepository;
    private final ProdutoRepository produtoRepository;

    public ImagemProdutoService(ImagemProdutoRepository imagemProdutoRepository,
                                ProdutoRepository produtoRepository) {
        this.imagemProdutoRepository = imagemProdutoRepository;
        this.produtoRepository = produtoRepository;
    }

    public List<ImagemProduto> listarPorProduto(UUID idProduto) {
        return imagemProdutoRepository.findByProdutoId(idProduto);
    }

    public ImagemProduto adicionarImagem(UUID idProduto, String url, boolean principal) {
        Produto produto = produtoRepository.findById(idProduto)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado"));

        // Se for principal, remove flag de outras imagens
        if (principal) {
            imagemProdutoRepository.desmarcarImagensPrincipais(produto.getId());
        }

        ImagemProduto imagem = new ImagemProduto();
        imagem.setProduto(produto);
        imagem.setUrl(url);
        imagem.setPrincipal(principal);

        return imagemProdutoRepository.save(imagem);
    }

    public void excluirImagem(UUID idImagem) {
        imagemProdutoRepository.deleteById(idImagem);
    }
}
