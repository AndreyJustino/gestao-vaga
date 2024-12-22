package com.andrey.rocketseat.gestao_vagas.modules.company.controllers;

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
    public ResponseEntity<Object> authCompanyController(@Valid @RequestBody AuthCompanyDTO authCompanyDTO){
    
        try {
            AuthCompanyResponseDTO token = authCompany.execute(authCompanyDTO);

            System.out.println("===== AuthCompanyController try ====");

            return ResponseEntity.ok().body(token);
        } catch (Exception e) {


            System.out.println("===== AuthCompanyController try ====");
            
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Erro ao autenticar empresa");
        }

        

    }
}
