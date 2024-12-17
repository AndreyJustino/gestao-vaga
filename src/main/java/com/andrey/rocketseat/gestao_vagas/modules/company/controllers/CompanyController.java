package com.andrey.rocketseat.gestao_vagas.modules.company.controllers;

import com.andrey.rocketseat.gestao_vagas.modules.company.entities.CompanyEntity;
import com.andrey.rocketseat.gestao_vagas.modules.company.service.CreateCompanyService;
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
    public ResponseEntity<Object> createCompanyController(@Valid @RequestBody CompanyEntity company){
        try{
            Object result = this.createCompanyService.createCompany(company);
            return ResponseEntity.ok().body(result);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }



    }
}
