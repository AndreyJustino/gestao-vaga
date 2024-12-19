package com.andrey.rocketseat.gestao_vagas.modules.company.dto;

import com.andrey.rocketseat.gestao_vagas.modules.company.Levels;

import lombok.Data;

@Data
public class CreateJobDTO {
    private String description;

    private Levels levels;

    private String benefits;
}
