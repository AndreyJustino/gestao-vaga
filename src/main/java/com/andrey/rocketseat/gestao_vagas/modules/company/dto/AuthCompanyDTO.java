package com.andrey.rocketseat.gestao_vagas.modules.company.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class AuthCompanyDTO {
    
    @Email
    @NotBlank(message = "O campo (email) não pode estar em branco")
    private String email;

    @NotBlank(message = "O campo (password) não pode estar em branco")
    private String password;
}
