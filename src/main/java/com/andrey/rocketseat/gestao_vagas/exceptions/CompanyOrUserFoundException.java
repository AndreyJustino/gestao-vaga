package com.andrey.rocketseat.gestao_vagas.exceptions;

public class CompanyOrUserFoundException extends RuntimeException {
    public CompanyOrUserFoundException(String message) {
        super(message);
    }
}
