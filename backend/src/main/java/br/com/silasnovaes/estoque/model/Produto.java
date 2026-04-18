package br.com.silasnovaes.estoque.model;

import br.com.silasnovaes.estoque.exception.EstoqueInsuficienteException;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Entidade que representa um Produto no sistema de estoque.
 * Utiliza o padrão de "Rich Domain Model" para encapsular as regras de negócio.
 */
@Entity
@Table(name = "tb_produtos")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 150)
    private String nome;

    @Column(nullable = false)
    private Double preco;

    @Column(nullable = false, name = "quantidade_estoque")
    private Integer quantidadeEstoque;

    /**
     * Processa a venda do produto, garantindo a integridade do estoque.
     * * @param quantidadeVendida A quantidade que está sendo vendida.
     * @throws EstoqueInsuficienteException se a quantidade vendida for maior que o estoque.
     */
    public void realizarVenda(Integer quantidadeVendida) {
        if (quantidadeVendida > this.quantidadeEstoque) {
            // A mensagem exata solicitada nos requisitos
            throw new EstoqueInsuficienteException("Estoque insuficiente para realizar a venda.");
        }
        this.quantidadeEstoque -= quantidadeVendida;
    }
}