package com.andrey.rocketseat.gestao_vagas.modules.candidate.controllers;

import com.andrey.rocketseat.gestao_vagas.modules.candidate.CandidateEntity;
import com.andrey.rocketseat.gestao_vagas.modules.candidate.dto.CandidateProfileDTO;
import com.andrey.rocketseat.gestao_vagas.modules.candidate.service.CreateCandidateService;
import com.andrey.rocketseat.gestao_vagas.modules.candidate.service.ListAllJobsByFilterService;
import com.andrey.rocketseat.gestao_vagas.modules.candidate.service.ProfileCandidateService;

import com.andrey.rocketseat.gestao_vagas.modules.company.entities.JobsEntity;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController//indicar que isso é uma controller
@RequestMapping("/candidate")//indicar em qual rota os metodos desse arquivo vão rodar
public class CandidateController {

    @Autowired
    private CreateCandidateService createCandidateService;

    @PostMapping("/")
    @Tag(name = "Candidato - Cadastro", description = "Endpoint responsável por realizar o cadastro de um novo candidato no sistema.")
    @Operation(summary = "Cadastrar candidato", description = "Realiza o cadastro de um novo candidato, armazenando suas informações no banco de dados.")
    @ApiResponse(responseCode = "200", content = {
            @Content(schema = @Schema(implementation = CandidateEntity.class))
    })
    public ResponseEntity<Object> create(@Valid @RequestBody CandidateEntity candidate){

        try{
            Object result = this.createCandidateService.execute(candidate);

            return ResponseEntity.ok().body(result);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }

    @Autowired
    private ProfileCandidateService profileCandidateService;

    @GetMapping("/profile")
    @PreAuthorize("hasRole('CANDIDATE')")
    @Tag(name = "Candidato - Perfil", description = "Endpoint para obter informações do perfil do candidato autenticado.")
    @Operation(summary = "Consultar perfil", description = "Retorna as informações do perfil do candidato autenticado com base no ID.")
    @ApiResponse(responseCode = "200", content = {
            @Content(schema = @Schema(implementation = CandidateProfileDTO.class))
    })
    @SecurityRequirement(name = "JWT_Auth")
    public ResponseEntity<Object> getProfileCandidate(HttpServletRequest request) {

        try{

            String id = request.getAttribute("candidate_id").toString();

            CandidateProfileDTO candidateProfileDTO = this.profileCandidateService
                .execute(UUID.fromString(id));

            return ResponseEntity.ok().body(candidateProfileDTO);


        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.NON_AUTHORITATIVE_INFORMATION).body(e.getMessage());
        }

    }

    @Autowired
    private ListAllJobsByFilterService listAllJobsByFilterService;

    @GetMapping("/jobs/list")
    @PreAuthorize("hasRole('CANDIDATE')")

    @Tag(name = "Vagas - Listagem", description = "Endpoint para listar vagas disponíveis de acordo com um filtro.")

    @Operation(summary = "Listar vagas", description = "Lista todas as vagas disponíveis cadastradas no sistema que correspondem ao filtro informado.")

    @ApiResponse(responseCode = "200", content = {
            @Content(array = @ArraySchema(schema = @Schema(implementation = JobsEntity.class)))
    })

    @SecurityRequirement(name = "JWT_Auth")
    public ResponseEntity<List<JobsEntity>> listAllJobsByFilterController(@RequestParam String filter){

        List<JobsEntity> result = this.listAllJobsByFilterService.execute(filter);

        return ResponseEntity.ok().body(result);
    }
}
