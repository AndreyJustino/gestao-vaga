package com.andrey.rocketseat.gestao_vagas.exceptions;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class DefaultHandlerExceptionController {

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handlerIllegalArgumentException(IllegalArgumentException e){

        return ResponseEntity.badRequest().body("Erro: " + e.getMessage());
    }
}
