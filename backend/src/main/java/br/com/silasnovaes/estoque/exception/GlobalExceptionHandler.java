package br.com.silasnovaes.estoque.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

/**
 * Interceptador global para padronizar o retorno de erros da API.
 * Garante que o frontend receba respostas consistentes e fáceis de tratar.
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Captura a nossa exceção de negócio (Estoque Insuficiente)
     */
    @ExceptionHandler(EstoqueInsuficienteException.class)
    public ResponseEntity<ErroPadrao> handleEstoqueInsuficiente(EstoqueInsuficienteException ex) {
        ErroPadrao erro = new ErroPadrao(
                LocalDateTime.now(),
                HttpStatus.BAD_REQUEST.value(),
                "Erro de Regra de Negócio",
                ex.getMessage()
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erro);
    }

    /**
     * Captura erros de validação dos DTOs (ex: campos vazios, valores negativos)
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErroPadrao> handleValidationErrors(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach(error -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });

        ErroPadrao erro = new ErroPadrao(
                LocalDateTime.now(),
                HttpStatus.BAD_REQUEST.value(),
                "Erro de Validação de Dados",
                errors.toString()
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erro);
    }

    /**
     * Captura qualquer outro erro inesperado (Fallback)
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErroPadrao> handleGenericException(Exception ex) {
        ErroPadrao erro = new ErroPadrao(
                LocalDateTime.now(),
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                "Erro Interno do Servidor",
                "Ocorreu um erro inesperado. Contate o administrador."
        );
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(erro);
    }

    /**
     * Record para padronizar o payload de erro retornado no JSON.
     */
    public record ErroPadrao(LocalDateTime timestamp, Integer status, String error, String message) {
    }
}