package com.andrey.rocketseat.gestao_vagas.modules.candidate.controllers;

import com.andrey.rocketseat.gestao_vagas.modules.candidate.CandidateEntity;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController//indicar que isso é uma controller
@RequestMapping("/candidate")//indicar em qual rota os metodos desse arquivo vão rodar
public class CandidateController {

    @PostMapping("/")
    public ResponseEntity create(@Valid @RequestBody CandidateEntity candidate){
        System.out.println(candidate.getEmail());
        return ResponseEntity.ok().body(candidate.getUsername());
    }
}
