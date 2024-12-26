package com.andrey.rocketseat.gestao_vagas.modules.company.controllers;

import com.andrey.rocketseat.gestao_vagas.modules.company.dto.CreateJobDTO;
import com.andrey.rocketseat.gestao_vagas.modules.company.entities.JobsEntity;
import com.andrey.rocketseat.gestao_vagas.modules.company.service.CreateJobService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/company")
public class JobController {
    @Autowired
    private CreateJobService createJobService;

    @PostMapping("/jobs")
    @PreAuthorize("hasRole('COMPANY')")
    @Tag(name = "Vaga - Cadastro", description = "Endpoint responsável por cadastrar uma nova vaga no sistema.")
    @Operation(summary = "Cadastrar vaga", description = "Realiza o cadastro de uma nova vaga de emprego, armazenando suas informações no banco de dados.")
    @ApiResponse(responseCode = "200", content = {
            @Content(schema = @Schema(implementation = JobsEntity.class))
    })
    public ResponseEntity<Object> createJobController(@Valid @RequestBody CreateJobDTO createJobDTO, HttpServletRequest request){
        try{

            Object companyId = request.getAttribute("company_id");
            
            JobsEntity jobsEntity = JobsEntity.builder()
                .benefits(createJobDTO.getBenefits())
                .companyId(UUID.fromString(companyId.toString()))
                .description(createJobDTO.getDescription())
                .levels(createJobDTO.getLevels())
                .build();

            //jobsEntity.setCompanyId();

            Object result = this.createJobService.createJobService(jobsEntity);
            return ResponseEntity.ok().body(result);
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
