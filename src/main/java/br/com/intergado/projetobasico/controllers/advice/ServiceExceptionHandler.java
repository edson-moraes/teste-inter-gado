package br.com.intergado.projetobasico.controllers.advice;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.server.ResponseStatusException;

@ControllerAdvice
@Slf4j
public class ServiceExceptionHandler {

    @ExceptionHandler(ResponseStatusException.class)
    protected ResponseEntity<Object> handleResponseStatusException(
            RuntimeException ex, WebRequest request) {
        ResponseStatusException e = (ResponseStatusException) ex;
        return ResponseEntity.status(e.getStatus()).body(null);
    }

    @ExceptionHandler(Exception.class)
    protected ResponseEntity<Object> handleException(RuntimeException ex, WebRequest request) {
        log.error("[Teste Inter Gado] Ocorreu um erro inesperado... {}", ex.getMessage());
        ex.printStackTrace();
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ops, ocorreu um erro!");
    }

}
