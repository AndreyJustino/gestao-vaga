package com.andrey.rocketseat.gestao_vagas.modules.candidate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDateTime;
import java.util.UUID;

@Data //usado para criar os getters e setter de forma automatica, vem do lombok
@Entity(name = "candidate") // indicando que isso vai ser uma tabela dentro do DB
public class CandidateEntity {
    @Id //dizendo que essa sera a chave primaria ID
    @GeneratedValue(strategy = GenerationType.UUID) //informando que é para gerar UUID
    private UUID id;

    @NotBlank(message = "O campo (name), não pode estar vazio.")
    private String name;

    @NotNull(message = "O campo (username), não pode ser nulo.")
    @Pattern(regexp = "^\\S+$", message = "O campo (username), não aceita espaços em brancos")
    private String username;

    @NotBlank(message = "O campo (email), não pode estar vazio.")
    @Email(message = "O campo (email), deve receber um e-mail valido.")
    private String email;

    @NotBlank(message = "O campo (password), não pode estar vazio.")
    @Length(min = 8, max = 16, message = "O valor minimo de caracteres pro campo (senha) é 8, e o maximo é 16.")
    private String password;

    private String description;
    private String curriculum;

    @CreationTimestamp
    private LocalDateTime createdAt;
}
