package com.andrey.rocketseat.gestao_vagas.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor//cria um constructor com todos os argumentos
public class ErrorMessageDTO {
    private String message;
    private String field;
}
