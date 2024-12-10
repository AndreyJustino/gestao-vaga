package com.andrey.rocketseat.gestao_vagas.exceptions;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.ArrayList;
import java.util.List;

/*
    Esse código serve para capturar erros específicos que acontecem
    quando alguém faz uma requisição errada na sua API. Ele intercepta
    esses erros e os transforma em uma resposta mais "bonitinha" e
    fácil de entender para quem está consumindo a API. Sem isso, o cliente
    (quem está usando sua API) receberia um erro super feio e técnico.

*/

/*diz ao Spring Boot que esta classe será responsável por "observar" e
capturar erros em todos os controladores da sua API.*/

@ControllerAdvice
public class ExceptionHandlerController {

    /*MessageSource é um recurso do Spring usado para buscar mensagens
     em arquivos de configuração, como mensagens de erro.*/
    private MessageSource messageSource;

    public ExceptionHandlerController(MessageSource messageSource){
        this.messageSource = messageSource;
    }

    /*A anotação @ExceptionHandler diz: "Ei, se acontecer um erro do
    tipo MethodArgumentNotValidException, me avise que eu vou cuidar disso!".*/

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<ErrorMessageDTO>> handleMethodArgumentValidException(MethodArgumentNotValidException e){

        List<ErrorMessageDTO> dto = new ArrayList<>(); //lista onde sera guardada as mensagens de erro

        // Pega todos os erros de validação relacionados aos campos da requisição
        e.getBindingResult().getFieldErrors().forEach(fieldError -> {

            // Recupera a mensagem de erro traduzida para o idioma do cliente (usando o MessageSource).
            // O `fieldError` contém informações sobre o erro no campo (nome do campo, mensagem padrão, etc.).
            // O `LocaleContextHolder.getLocale()` obtém o idioma atual do cliente (ex.: pt-BR ou en-US).
            String message = messageSource.getMessage(fieldError, LocaleContextHolder.getLocale());

            // Cria um objeto do tipo ErrorMessageDTO com duas informações:
            // 1. A mensagem de erro amigável que foi recuperada acima (ex.: "O campo (email) deve receber um e-mail válido").
            // 2. O nome do campo que deu erro (ex.: "email").
            ErrorMessageDTO errorMessageDTO = new ErrorMessageDTO(message, fieldError.getField());

            // Adiciona o objeto ErrorMessageDTO à lista `dto`, que será retornada ao cliente no final.
            dto.add(errorMessageDTO);
        });


        return new ResponseEntity<>(dto, HttpStatus.BAD_REQUEST);
    }
}
