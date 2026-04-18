/**
 * Módulo de Manipulação de Interface (UI).
 * Responsável por renderizar elementos e gerenciar feedbacks visuais.
 */
export const UI = {
    /**
     * Cria e exibe uma notificação Toast na tela.
     */
    showToast(message, type = 'success') {
        const container = document.getElementById('toast-container');
        const toast = document.createElement('div');
        toast.className = `c-toast c-toast--${type}`;
        
        const icon = type === 'success' ? 'ph-check-circle' : 'ph-warning-circle';
        
        toast.innerHTML = `
            <i class="ph-fill ${icon} c-toast__icon"></i>
            <p class="c-toast__text">${message}</p>
        `;

        container.appendChild(toast);

        // Remove o toast após 4 segundos com animação
        setTimeout(() => {
            toast.classList.add('is-closing');
            toast.addEventListener('animationend', () => toast.remove());
        }, 4000);
    },

    /**
     * Renderiza a lista de produtos no Grid.
     */
    renderProdutos(produtos, onVendaCallback) {
        const grid = document.getElementById('grid-produtos');
        const contador = document.getElementById('contador-produtos');
        
        grid.innerHTML = '';
        contador.textContent = `${produtos.length} itens`;

        if (produtos.length === 0) {
            grid.innerHTML = '<p class="u-text-center u-mb-4">Nenhum produto cadastrado.</p>';
            return;
        }

        produtos.forEach(produto => {
            const card = document.createElement('div');
            card.className = 'c-product-card';
            
            // Lógica de visual de estoque baixo
            let stockClass = '';
            if (produto.quantidade_estoque === 0) stockClass = 'c-product-card__stock--out';
            else if (produto.quantidade_estoque < 5) stockClass = 'c-product-card__stock--low';

            card.innerHTML = `
                <div class="c-product-card__image-wrapper">
                    <img src="assets/img/produto-generico.jpg" alt="${produto.nome}" class="c-product-card__image" 
                         onerror="this.src='https://placehold.co/400x300/e2e8f0/64748b?text=Produto'">
                </div>
                <div class="c-product-card__body">
                    <h3 class="c-product-card__title">${produto.nome}</h3>
                    <span class="c-product-card__price">R$ ${produto.preco.toFixed(2)}</span>
                    <span class="c-product-card__stock ${stockClass}">
                        <i class="ph-fill ph-archive"></i> 
                        ${produto.quantidade_estoque === 0 ? 'Esgotado' : `Estoque: ${produto.quantidade_estoque}`}
                    </span>
                </div>
                <div class="c-product-card__footer">
                    <button class="c-button c-button--primary btn-vender" data-id="${produto.id}">
                        Vender 1 unidade
                    </button>
                </div>
            `;

            const btnVender = card.querySelector('.btn-vender');
            btnVender.onclick = () => onVendaCallback(produto.id, 1);
            
            grid.appendChild(card);
        });
    }
};