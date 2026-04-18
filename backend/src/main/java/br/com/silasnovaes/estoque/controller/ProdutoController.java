package br.com.silasnovaes.estoque.controller;

import br.com.silasnovaes.estoque.dto.ProdutoRequestDTO;
import br.com.silasnovaes.estoque.dto.ProdutoResponseDTO;
import br.com.silasnovaes.estoque.dto.VendaRequestDTO;
import br.com.silasnovaes.estoque.service.ProdutoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controlador REST para gerenciar as operações de Produtos e Vendas.
 * Expõe os endpoints da API garantindo o uso correto dos verbos HTTP e status codes.
 */
@RestController
@RequestMapping("/produtos")
@RequiredArgsConstructor
public class ProdutoController {

    private final ProdutoService produtoService;

    /**
     * Endpoint para cadastrar um novo produto.
     * Retorna 201 (Created) em caso de sucesso.
     */
    @PostMapping
    public ResponseEntity<ProdutoResponseDTO> cadastrar(@RequestBody @Valid ProdutoRequestDTO request) {
        ProdutoResponseDTO response = produtoService.cadastrarProduto(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    /**
     * Endpoint para listar todos os produtos disponíveis.
     * Retorna 200 (OK).
     */
    @GetMapping
    public ResponseEntity<List<ProdutoResponseDTO>> listarTodos() {
        List<ProdutoResponseDTO> response = produtoService.listarTodos();
        return ResponseEntity.ok(response);
    }

    /**
     * Endpoint para processar uma venda e baixar o estoque.
     * Retorna 200 (OK) com o produto atualizado.
     */
    @PostMapping("/{id}/vender")
    public ResponseEntity<ProdutoResponseDTO> vender(
            @PathVariable Long id,
            @RequestBody @Valid VendaRequestDTO request) {
        ProdutoResponseDTO response = produtoService.realizarVenda(id, request);
        return ResponseEntity.ok(response);
    }
}