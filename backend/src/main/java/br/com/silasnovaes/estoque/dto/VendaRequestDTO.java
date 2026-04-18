package br.com.silasnovaes.estoque.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

/**
 * DTO para recebimento dos dados de processamento de uma venda.
 * Isola a intenção da operação no domínio, garantindo que apenas 
 * os dados necessários para a baixa de estoque trafeguem pela rede.
 */
public record VendaRequestDTO(

        @NotNull(message = "A quantidade a ser vendida é obrigatória.")
        @Positive(message = "A quantidade a ser vendida deve ser maior que zero.")
        Integer quantidadeVendida
) {
}