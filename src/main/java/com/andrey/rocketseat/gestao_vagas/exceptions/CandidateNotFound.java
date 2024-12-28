package com.andrey.rocketseat.gestao_vagas.exceptions;

public class CandidateNotFound extends RuntimeException{
    public CandidateNotFound(){
        super("Candidato não encontrado");
    }
}
