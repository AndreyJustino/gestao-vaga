package com.andrey.rocketseat.gestao_vagas.modules.company.entities;

import com.andrey.rocketseat.gestao_vagas.modules.company.Levels;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity(name = "job")
@Data
@Builder
@AllArgsConstructor//fazer um constructor com todos os atributos
@NoArgsConstructor//fazer um constructor sem os atributos
public class JobsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @NotBlank(message = "Descrição da vaga é obrigatoria")
    @Schema(example = "Esta vaga é para que quer ser dev java em um ambiente super legal", requiredMode = Schema.RequiredMode.REQUIRED)
    private String description;

    @NotNull(message = "O campo (levels) não pode ser nulo")
    @Enumerated(EnumType.STRING) // Salva os valores do enum como strings no banco
    @Schema(examples = {
            "ESTAGIARIO",
            "JUNIOR",
            "PLENO",
            "SENIOR"
    }, requiredMode = Schema.RequiredMode.REQUIRED)
    private Levels levels;

    @Schema(example = "Salario, ferias remuneradas, folga uma vez na seman")
    private String benefits;

    @ManyToOne
    @JoinColumn(name = "company_id", insertable=false, updatable=false)
    /*joinColum: Define qual será a coluna dessa classe no banco de dados
     que será usada para fazer o vínculo entre as tabelas.*/
    private CompanyEntity companyEntity;

    @NotNull(message = "O campo (companyId não pode ser nulo)")
    @Column(name = "company_id", nullable = false)
    private UUID companyId;

    @CreationTimestamp
    private LocalDateTime createdAt;
}
