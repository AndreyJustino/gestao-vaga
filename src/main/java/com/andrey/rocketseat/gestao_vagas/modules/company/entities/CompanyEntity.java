package com.andrey.rocketseat.gestao_vagas.modules.company.entities;

import io.swagger.v3.oas.annotations.media.Schema;
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
    @Schema(example = "Javagas", requiredMode = Schema.RequiredMode.REQUIRED)
    private String name;

    @Pattern(regexp = "^(\\d{2})\\.(\\d{3})\\.(\\d{3})\\/\\d{4}\\-(\\d{2})$", message = "Passe um cnpj no formato XX.XXX.XXX/XXXX-XX")
    @Schema(example = "12.123.123/1234-12", requiredMode = Schema.RequiredMode.REQUIRED)
    private String cnpj;

    @NotBlank
    @Schema(example = "javagas_java_vagas", requiredMode = Schema.RequiredMode.REQUIRED)
    private String username;

    @Email
    @NotBlank
    @Schema(example = "javagas@mail.com", requiredMode = Schema.RequiredMode.REQUIRED)
    private String email;

    @Length(max = 70, min = 8, message = "O valor minimo de caracteres pro campo (password) é 8, e o maximo é 70.")
    @Schema(example = "12345678", requiredMode = Schema.RequiredMode.REQUIRED, minLength = 8, maxLength = 70)
    private String password;

    @Schema(example = "javagas.com.br")
    private String website;

    @Schema(example = "empresa legal que oferece vagas de dev java")
    private String description;

    @CreationTimestamp
    private LocalDateTime createdAt;
}
