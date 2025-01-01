package com.andrey.rocketseat.gestao_vagas.modules.company.dto;

import com.andrey.rocketseat.gestao_vagas.modules.company.Levels;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateJobDTO {
    @Schema(example = "Vaga para estagario com no minimo 2 anos de expericiencia com java", requiredMode = Schema.RequiredMode.REQUIRED)
    private String description;

    @Schema(examples = {
            "ESTAGIARIO",
            "JUNIOR",
            "PLENO",
            "SENIOR"
    }, requiredMode = Schema.RequiredMode.REQUIRED)
    private Levels levels;

    @Schema(example = "Salario", requiredMode = Schema.RequiredMode.REQUIRED)
    private String benefits;
}
