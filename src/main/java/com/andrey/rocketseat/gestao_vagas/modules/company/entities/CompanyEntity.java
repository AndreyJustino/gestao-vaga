package com.andrey.rocketseat.gestao_vagas.modules.company.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity(name = "company") // indicando que isso sera uma tabela
@Data //dizendo pro spring cria os getter e setter
public class CompanyEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @NotBlank(message = "O campo (name) não pode estar vazio ou ser nulo")
    private String name;

    @Pattern(regexp = "^(\\d{2})\\.(\\d{3})\\.(\\d{3})\\/\\d{4}\\-(\\d{2})$", message = "Passe um cnpj no formato XX.XXX.XXX/XXXX-XX")
    private String cnpj;

    @NotBlank
    private String username;

    @Email
    @NotBlank
    private String email;

    @Length(max = 70, min = 8, message = "O valor minimo de caracteres pro campo (password) é 8, e o maximo é 70.")
    private String password;

    private String website;
    private String description;

    @CreationTimestamp
    private LocalDateTime createdAt;
}
