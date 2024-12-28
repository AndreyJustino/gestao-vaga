package com.andrey.rocketseat.gestao_vagas.exceptions;

public class JobNotFound extends RuntimeException {
    public JobNotFound() {
        super("Vaga não encontrada.");
    }
}
