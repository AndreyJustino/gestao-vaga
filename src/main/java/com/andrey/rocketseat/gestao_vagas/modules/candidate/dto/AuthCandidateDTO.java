package com.andrey.rocketseat.gestao_vagas.modules.candidate.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class AuthCandidateDTO {
    
    @Email
    @NotBlank(message = "O campo (email) não pode estar vazio")
    private String email;

    @NotBlank(message = "O campo (password) não pode estar vazio")
    private String password;
}
