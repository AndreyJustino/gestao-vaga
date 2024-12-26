package com.andrey.rocketseat.gestao_vagas.modules.candidate.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.andrey.rocketseat.gestao_vagas.modules.candidate.dto.AuthCandidateDTO;
import com.andrey.rocketseat.gestao_vagas.modules.candidate.dto.AuthCandidateResponseDTO;
import com.andrey.rocketseat.gestao_vagas.modules.candidate.service.AuthCandidateService;

import jakarta.security.auth.message.AuthException;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/candidate")
public class AuthCandidateController {
    @Autowired
    private AuthCandidateService authCandidateService;

    @PostMapping("/auth")
    @Tag(name = "Usuário - Autenticação", description = "Endpoint responsável por autenticar o usuário candidato e retornar um token de acesso.")
    @Operation(summary = "Autenticar usuário", description = "Realiza a autenticação do usuário candidato com base nas credenciais fornecidas e retorna um token de acesso para autorização nas operações futuras.")
    @ApiResponse(responseCode = "200", content = {
            @Content(schema = @Schema(implementation = AuthCandidateResponseDTO.class))
    })
    public ResponseEntity<Object> authCandidateController(@Valid @RequestBody AuthCandidateDTO authCandidateDTO){

        try {
            AuthCandidateResponseDTO token = this.authCandidateService.execute(authCandidateDTO);

            System.out.println("===== authCandidateController try ====");

            return ResponseEntity.ok().body(token);
        } catch (AuthException e) {
            System.out.println("===== authCandidateController catch ====");
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        }
        
    }
}
