package com.andrey.rocketseat.gestao_vagas.modules.company.controllers;

import com.andrey.rocketseat.gestao_vagas.modules.company.entities.CompanyEntity;
import com.andrey.rocketseat.gestao_vagas.modules.company.service.CreateCompanyService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/company")
public class CompanyController {
    @Autowired
    private CreateCompanyService createCompanyService;

    @PostMapping("/")
    @Tag(name = "Empresa - Cadastro", description = "Endpoint responsável por cadastrar uma nova empresa no sistema.")
    @Operation(summary = "Cadastrar empresa", description = "Realiza o cadastro de uma nova empresa, armazenando suas informações no banco de dados.")
    @ApiResponse(responseCode = "200", description = "Empresa cadastrada com sucesso", content = {
            @Content(schema = @Schema(implementation = CompanyEntity.class))
    })
    public ResponseEntity<Object> createCompanyController(@Valid @RequestBody CompanyEntity company){
        try{
            Object result = this.createCompanyService.createCompany(company);
            return ResponseEntity.ok().body(result);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }



    }
}
