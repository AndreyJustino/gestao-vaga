package com.andrey.rocketseat.gestao_vagas.modules.candidate.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class AuthCandidateDTO {
    
    @Email
    @NotBlank(message = "O campo (email) não pode estar vazio")
    @Schema(example = "seuemail@mail.com", requiredMode = Schema.RequiredMode.REQUIRED)
    private String email;

    @NotBlank(message = "O campo (password) não pode estar vazio")
    @Schema(example = "12345678", requiredMode = Schema.RequiredMode.REQUIRED, minLength = 8, maxLength = 70)
    private String password;
}
