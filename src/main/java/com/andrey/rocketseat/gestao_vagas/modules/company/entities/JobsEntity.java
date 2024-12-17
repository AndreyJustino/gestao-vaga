package com.andrey.rocketseat.gestao_vagas.modules.company.entities;

import com.andrey.rocketseat.gestao_vagas.modules.company.Levels;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity(name = "job")
@Data
public class JobsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @NotBlank(message = "Descrição da vaga é obrigatoria")
    private String description;

    private Levels levels;

    private String benefits;

    @ManyToOne
    @JoinColumn(name = "company_id")
    /*joinColum: Define qual será a coluna dessa classe no banco de dados
     que será usada para fazer o vínculo entre as tabelas.*/
    private CompanyEntity companyEntity;

    @Column(name = "company_id", insertable=false, updatable=false)
    private UUID companyId;

    @CreationTimestamp
    private LocalDateTime createdAt;
}
