import { ProdutoAPI } from './modules/api.js';
import { UI } from './modules/ui.js';

document.addEventListener('DOMContentLoaded', () => {
    const formCadastro = document.getElementById('form-cadastro');
    const themeToggle = document.getElementById('theme-toggle');
    const body = document.body;

    // --- Lógica de Inicialização ---
    async function init() {
        try {
            const produtos = await ProdutoAPI.listarTodos();
            UI.renderProdutos(produtos, handleVenda);
        } catch (error) {
            UI.showToast(error.message, 'error');
        }
    }

    // --- Lógica de Venda ---
    async function handleVenda(id, quantidade) {
        try {
            await ProdutoAPI.vender(id, quantidade);
            UI.showToast('Venda realizada com sucesso!');
            init(); // Recarrega a lista
        } catch (error) {
            UI.showToast(error.message, 'error'); // Aqui aparecerá "Estoque insuficiente..."
        }
    }

    // --- Evento de Cadastro ---
    formCadastro.addEventListener('submit', async (e) => {
        e.preventDefault();
        
        const formData = {
            nome: document.getElementById('nome').value,
            preco: parseFloat(document.getElementById('preco').value),
            quantidade_estoque: parseInt(document.getElementById('quantidadeEstoque').value)
        };

        try {
            await ProdutoAPI.cadastrar(formData);
            UI.showToast('Produto cadastrado com sucesso!');
            formCadastro.reset();
            init();
        } catch (error) {
            UI.showToast(error.message, 'error');
        }
    });

    // --- Mágica: Dark Mode & Easter Egg ---
    themeToggle.addEventListener('click', () => {
        const isDark = body.classList.toggle('theme-dark');
        body.classList.toggle('theme-light', !isDark);
        
        // Troca o ícone do botão
        const icon = themeToggle.querySelector('i');
        icon.className = isDark ? 'ph ph-sun c-theme-toggle__icon' : 'ph ph-moon c-theme-toggle__icon';
        
        if(isDark) {
            console.log("%cEaster Egg Ativado! Olá, Professor!", "color: #10b981; font-weight: bold; font-size: 14px;");
        }
    });

    // Iniciar app
    init();
});