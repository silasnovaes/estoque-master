package br.com.silasnovaes.estoque.service;

import br.com.silasnovaes.estoque.dto.ProdutoRequestDTO;
import br.com.silasnovaes.estoque.dto.ProdutoResponseDTO;
import br.com.silasnovaes.estoque.dto.VendaRequestDTO;
import br.com.silasnovaes.estoque.model.Produto;
import br.com.silasnovaes.estoque.repository.ProdutoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

/**
 * Camada de serviço responsável por orquestrar os fluxos de negócio.
 * Faz a ponte entre os controladores da API e a camada de persistência.
 */
@Service
@RequiredArgsConstructor
public class ProdutoService {

    private final ProdutoRepository produtoRepository;

    /**
     * Cadastra um novo produto no sistema.
     */
    @Transactional
    public ProdutoResponseDTO cadastrarProduto(ProdutoRequestDTO request) {
        Produto produto = Produto.builder()
                .nome(request.nome())
                .preco(request.preco())
                .quantidadeEstoque(request.quantidadeEstoque())
                .build();
        
        produto = produtoRepository.save(produto);
        return ProdutoResponseDTO.fromEntity(produto);
    }

    /**
     * Processa a venda de um produto, abstraindo a lógica de baixa de estoque.
     */
    @Transactional
    public ProdutoResponseDTO realizarVenda(Long id, VendaRequestDTO request) {
        Produto produto = produtoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Produto não encontrado."));
        
        // Delega a regra de negócio rigorosa para a própria entidade (Rich Domain Model)
        produto.realizarVenda(request.quantidadeVendida());
        
        produto = produtoRepository.save(produto);
        return ProdutoResponseDTO.fromEntity(produto);
    }

    /**
     * Lista todos os produtos cadastrados.
     * Utiliza readOnly = true para otimizar a performance da query no banco.
     */
    @Transactional(readOnly = true)
    public List<ProdutoResponseDTO> listarTodos() {
        return produtoRepository.findAll().stream()
                .map(ProdutoResponseDTO::fromEntity)
                .toList();
    }
}