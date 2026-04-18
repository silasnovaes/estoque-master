package br.com.silasnovaes.estoque.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;

/**
 * DTO para recebimento de dados de criação de um novo Produto.
 * Utiliza o recurso Record do Java para garantir imutabilidade e concisão.
 */
public record ProdutoRequestDTO(
        
        @NotBlank(message = "O nome do produto é obrigatório.")
        String nome,

        @NotNull(message = "O preço do produto é obrigatório.")
        @Positive(message = "O preço deve ser maior que zero.")
        Double preco,

        @NotNull(message = "A quantidade inicial em estoque é obrigatória.")
        @PositiveOrZero(message = "A quantidade em estoque não pode ser negativa.")
        Integer quantidadeEstoque
) {
}