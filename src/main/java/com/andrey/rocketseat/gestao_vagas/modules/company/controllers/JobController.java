package com.andrey.rocketseat.gestao_vagas.modules.company.controllers;

import com.andrey.rocketseat.gestao_vagas.modules.company.dto.CreateJobDTO;
import com.andrey.rocketseat.gestao_vagas.modules.company.entities.JobsEntity;
import com.andrey.rocketseat.gestao_vagas.modules.company.service.CreateJobService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/jobs")
public class JobController {
    @Autowired
    private CreateJobService createJobService;

    @PostMapping("/")
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
