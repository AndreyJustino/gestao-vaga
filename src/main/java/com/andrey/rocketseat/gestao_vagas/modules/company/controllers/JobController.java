package com.andrey.rocketseat.gestao_vagas.modules.company.controllers;

import com.andrey.rocketseat.gestao_vagas.modules.company.entities.JobsEntity;
import com.andrey.rocketseat.gestao_vagas.modules.company.service.CreateJobService;
import jakarta.validation.Valid;
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
    public ResponseEntity<Object> createJobController(@Valid @RequestBody JobsEntity jobsEntity){
        try{
            Object result = this.createJobService.createJobService(jobsEntity);
            return ResponseEntity.ok().body(result);
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
