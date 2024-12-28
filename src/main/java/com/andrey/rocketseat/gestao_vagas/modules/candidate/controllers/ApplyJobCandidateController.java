package com.andrey.rocketseat.gestao_vagas.modules.candidate.controllers;

import com.andrey.rocketseat.gestao_vagas.modules.candidate.dto.ApplyJobCandidateControllerDTO;
import com.andrey.rocketseat.gestao_vagas.modules.candidate.entity.ApplyJobCandidateEntity;
import com.andrey.rocketseat.gestao_vagas.modules.candidate.service.ApplyJobCandidateService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/candidate")
public class ApplyJobCandidateController {

    @Autowired
    private ApplyJobCandidateService applyJobCandidateService;

    @PreAuthorize("hasRole('CANDIDATE')")
    @PostMapping("/job/apply")
    @SecurityRequirement(name = "JWT_Auth")
    @Tag(name = "Candidatar - Vaga ")
    @Operation(summary = "Inscrição do candidato a uma vaga", description = "Função que possibilita o candidato a se inscrever a uma vaga")
    @ApiResponse(responseCode = "200", content = {
            @Content(schema = @Schema(implementation = ApplyJobCandidateEntity.class))
    })
    public ResponseEntity<Object> ApplyJobCandidateController(HttpServletRequest request, @RequestBody ApplyJobCandidateControllerDTO applyJob) {

        String idCandidate = request.getAttribute("candidate_id").toString();

        try{

            ApplyJobCandidateEntity result = this.applyJobCandidateService.execute(applyJob.getIdJob(), UUID.fromString(idCandidate));

            return ResponseEntity.ok().body(result);

        }catch (Exception e){
            e.printStackTrace();

            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
