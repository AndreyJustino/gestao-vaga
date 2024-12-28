package com.andrey.rocketseat.gestao_vagas.modules.candidate.entity;

import com.andrey.rocketseat.gestao_vagas.modules.candidate.CandidateEntity;
import com.andrey.rocketseat.gestao_vagas.modules.company.entities.JobsEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "apply_jobs")
public class ApplyJobCandidateEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    //não coloque @notblank nesse negocio com @joinColumn da ruim
    /*: Especifica que a esta coluna vai ser uma chave estrangeira
    Que vai fazer referência à chave primária da outra tabela no caso a tabela da CandidateEntity.*/
    @JoinColumn(name = "candidate_id", insertable = false, updatable = false)
    @ManyToOne
    private CandidateEntity candidateEntity;

    //não coloque @notblank nesse negocio com @joinColumn da ruim
    @JoinColumn(name = "job_id", insertable = false, updatable = false)
    @ManyToOne
    private JobsEntity jobsEntity;


    @Column(name = "candidate_id") //nomeando a coluna
    private UUID candidateId;


    @Column(name = "job_id") //nomeando a coluna
    private UUID jobId;

    @CreationTimestamp
    private LocalDateTime createdAt;
}
