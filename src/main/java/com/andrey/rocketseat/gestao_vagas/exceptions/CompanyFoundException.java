package com.andrey.rocketseat.gestao_vagas.exceptions;

public class CompanyFoundException extends RuntimeException {
    public CompanyFoundException() {
        super("Companhia ja existente!");
    }
}
