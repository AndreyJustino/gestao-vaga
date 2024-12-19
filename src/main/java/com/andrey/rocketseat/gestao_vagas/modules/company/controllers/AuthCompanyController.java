package com.andrey.rocketseat.gestao_vagas.modules.company.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.andrey.rocketseat.gestao_vagas.modules.company.dto.AuthCompanyDTO;
import com.andrey.rocketseat.gestao_vagas.modules.company.service.AuthCompanyService;

import jakarta.validation.Valid;

@RequestMapping("/auth")
@RestController
public class AuthCompanyController {

    @Autowired
    private AuthCompanyService authCompany;

    @PostMapping("/company")
    public ResponseEntity<Object> authCompanyController(@Valid @RequestBody AuthCompanyDTO authCompanyDTO){
     
        try {
            String token = authCompany.execute(authCompanyDTO);
            return ResponseEntity.ok().body(token);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        }

        

    }
}
