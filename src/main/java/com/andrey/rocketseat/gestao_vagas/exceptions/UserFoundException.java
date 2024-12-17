package com.andrey.rocketseat.gestao_vagas.exceptions;

public class UserFoundException extends RuntimeException{
    public UserFoundException(){
        super("Usuario ja cadastrado!");
    }
}
