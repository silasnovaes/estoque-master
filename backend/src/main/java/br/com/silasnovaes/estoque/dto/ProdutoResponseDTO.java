package br.com.silasnovaes.estoque.dto;

import br.com.silasnovaes.estoque.model.Produto;

/**
 * DTO para envio de dados do Produto para o cliente (Frontend).
 * Isola a entidade de banco de dados da representação da API.
 * Utiliza o recurso Record do Java para garantir imutabilidade.
 */
public record ProdutoResponseDTO(
        Long id,
        String nome,
        Double preco,
        Integer quantidadeEstoque
) {
    /**
     * Método utilitário (Factory Method) para converter uma entidade Produto em DTO.
     * Mantém o código das camadas superiores limpo e coeso.
     * * @param produto A entidade Produto vinda do banco de dados.
     * @return Uma nova instância de ProdutoResponseDTO.
     */
    public static ProdutoResponseDTO fromEntity(Produto produto) {
        return new ProdutoResponseDTO(
                produto.getId(),
                produto.getNome(),
                produto.getPreco(),
                produto.getQuantidadeEstoque()
        );
    }
}