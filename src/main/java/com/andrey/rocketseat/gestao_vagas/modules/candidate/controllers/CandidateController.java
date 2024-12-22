package com.andrey.rocketseat.gestao_vagas.modules.candidate.controllers;

import com.andrey.rocketseat.gestao_vagas.modules.candidate.CandidateEntity;
import com.andrey.rocketseat.gestao_vagas.modules.candidate.dto.CandidateProfileDTO;
import com.andrey.rocketseat.gestao_vagas.modules.candidate.service.CreateCandidateService;
import com.andrey.rocketseat.gestao_vagas.modules.candidate.service.ProfileCandidateService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController//indicar que isso é uma controller
@RequestMapping("/candidate")//indicar em qual rota os metodos desse arquivo vão rodar
public class CandidateController {

    @Autowired
    private CreateCandidateService createCandidateService;

    @PostMapping("/")
    public ResponseEntity<Object> create(@Valid @RequestBody CandidateEntity candidate){

        try{
            Object result = this.createCandidateService.execute(candidate);

            System.out.println("===== CandidateController try ====");

            return ResponseEntity.ok().body(result);
        } catch (Exception e) {
            System.out.println("===== CandidateController catch ====");
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }

    @Autowired
    private ProfileCandidateService profileCandidateService;

    @GetMapping("/profile")
    @PreAuthorize("hasRole('CANDIDATE')")
    public ResponseEntity<Object> getProfileCandidate(HttpServletRequest request) {
        
        try{

            String id = request.getAttribute("candidate_id").toString();

            CandidateProfileDTO candidateProfileDTO = this.profileCandidateService
                .execute(UUID.fromString(id));


            System.out.println("===== Candidate Controller Profile try ====");    
            return ResponseEntity.ok().body(candidateProfileDTO);


        }catch(Exception e){
            System.out.println("===== Candidate Controller Profile catch ====");
            e.getMessage();
            return ResponseEntity.status(HttpStatus.NON_AUTHORITATIVE_INFORMATION).body("deu ruim aqui");
        }
        
    }
}
