package com.andrey.rocketseat.gestao_vagas.modules.candidate.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CandidateProfileDTO {
    @Schema(example = "Andrey Justino", requiredMode = Schema.RequiredMode.REQUIRED)
    private String name;

    @Schema(example = "andrey_justino", requiredMode = Schema.RequiredMode.REQUIRED)
    private String username;

    @Schema(example = "seuemail@mail.com", requiredMode = Schema.RequiredMode.REQUIRED)
    private String email;

    @Schema(example = "Profissional muito bom")
    private String description;

    @Schema(example = "curriculoProfissionalBom.pdf")
    private String curriculum;
}

