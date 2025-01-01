package com.andrey.rocketseat.gestao_vagas.exceptions;

public class CompanyNotFoundException extends RuntimeException {
    public CompanyNotFoundException() {
        super("Companhia não encontrada/Company not found.");
    }
}
