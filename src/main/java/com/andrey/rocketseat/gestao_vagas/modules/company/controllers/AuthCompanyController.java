package com.andrey.rocketseat.gestao_vagas.modules.company.controllers;

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

import com.andrey.rocketseat.gestao_vagas.modules.company.dto.AuthCompanyDTO;
import com.andrey.rocketseat.gestao_vagas.modules.company.dto.AuthCompanyResponseDTO;
import com.andrey.rocketseat.gestao_vagas.modules.company.service.AuthCompanyService;

import jakarta.validation.Valid;

@RequestMapping("/company")
@RestController
public class AuthCompanyController {

    @Autowired
    private AuthCompanyService authCompany;

    @PostMapping("/auth")
    @Tag(name = "Empresa - Autenticação", description = "Endpoint responsável por autenticar uma empresa e retornar um token de acesso.")
    @Operation(summary = "Autenticar empresa", description = "Realiza a autenticação de uma empresa com base nas credenciais fornecidas e retorna um token de acesso para autorização nas operações futuras.")
    @ApiResponse(responseCode = "200", content = {
            @Content(schema = @Schema(implementation = AuthCompanyResponseDTO.class))
    })
    public ResponseEntity<Object> authCompanyController(@Valid @RequestBody AuthCompanyDTO authCompanyDTO){
    
        try {
            AuthCompanyResponseDTO token = authCompany.execute(authCompanyDTO);

            return ResponseEntity.ok().body(token);
        } catch (Exception e) {
            
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Erro ao autenticar empresa");
        }

        

    }
}
