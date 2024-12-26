package com.andrey.rocketseat.gestao_vagas.modules.company.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class AuthCompanyDTO {
    
    @Email
    @NotBlank(message = "O campo (email) não pode estar em branco")
    @Schema(example = "seuemail@mail.com", requiredMode = Schema.RequiredMode.REQUIRED)
    private String email;

    @NotBlank(message = "O campo (password) não pode estar em branco")
    @Schema(example = "12345678", requiredMode = Schema.RequiredMode.REQUIRED, minLength = 8, maxLength = 70)
    private String password;
}
