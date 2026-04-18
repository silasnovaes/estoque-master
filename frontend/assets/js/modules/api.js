/**
 * Módulo de integração com a API Spring Boot.
 * Centraliza as chamadas de rede utilizando Fetch API.
 */
const BASE_URL = 'http://localhost:8080/api/v1/produtos';

export const ProdutoAPI = {
    /**
     * Busca todos os produtos do banco de dados.
     */
    async listarTodos() {
        const response = await fetch(BASE_URL);
        if (!response.ok) throw new Error('Erro ao carregar produtos.');
        return await response.json();
    },

    /**
     * Envia um novo produto para cadastro.
     */
    async cadastrar(produto) {
        const response = await fetch(BASE_URL, {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(produto)
        });
        if (!response.ok) {
            const errorData = await response.json();
            throw new Error(errorData.message || 'Erro ao cadastrar produto.');
        }
        return await response.json();
    },

    /**
     * Realiza a baixa de estoque de um produto específico.
     */
    async vender(id, quantidade) {
        const response = await fetch(`${BASE_URL}/${id}/vender`, {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify({ quantidade_vendida: quantidade }) // SNAKE_CASE conforme configuramos no Backend
        });
        
        if (!response.ok) {
            const errorData = await response.json();
            throw new Error(errorData.message || 'Erro ao realizar venda.');
        }
        return await response.json();
    }
};