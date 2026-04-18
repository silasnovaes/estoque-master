package br.com.silasnovaes.estoque.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Exceção de negócio lançada quando há tentativa de realizar uma venda 
 * com quantidade superior ao estoque disponível.
 */
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class EstoqueInsuficienteException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public EstoqueInsuficienteException(String message) {
        super(message);
    }
}