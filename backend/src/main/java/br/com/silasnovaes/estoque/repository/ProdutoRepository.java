package br.com.silasnovaes.estoque.repository;

import br.com.silasnovaes.estoque.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Interface responsável pela comunicação com o banco de dados para a entidade Produto.
 * Estende JpaRepository para herdar operações padrão de CRUD e paginação,
 * mantendo o código limpo e livre de queries repetitivas (Boilerplate).
 */
@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {
    
    // O Spring Data JPA já fornece métodos como save(), findById(), findAll(), etc.
    // Futuramente, se precisarmos de buscas complexas (ex: buscar por nome), 
    // basta declarar a assinatura do método aqui.
}