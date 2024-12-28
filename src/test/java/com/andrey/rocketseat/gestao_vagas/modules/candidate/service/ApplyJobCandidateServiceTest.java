package com.andrey.rocketseat.gestao_vagas.modules.candidate.service;

import com.andrey.rocketseat.gestao_vagas.exceptions.CandidateNotFound;
import com.andrey.rocketseat.gestao_vagas.exceptions.CompanyOrUserFoundException;
import com.andrey.rocketseat.gestao_vagas.exceptions.JobNotFound;
import com.andrey.rocketseat.gestao_vagas.modules.candidate.CandidateEntity;
import com.andrey.rocketseat.gestao_vagas.modules.candidate.entity.ApplyJobCandidateEntity;
import com.andrey.rocketseat.gestao_vagas.modules.candidate.repository.ApplyJobRepository;
import com.andrey.rocketseat.gestao_vagas.modules.candidate.repository.CandidateRepository;
import com.andrey.rocketseat.gestao_vagas.modules.company.entities.JobsEntity;
import com.andrey.rocketseat.gestao_vagas.modules.company.repository.JobRepository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

//teste unitario (testando metodos/funcoes especificas)
@ExtendWith(MockitoExtension.class)
public class ApplyJobCandidateServiceTest {

    @Mock //cria uma instancia de uma classe, porém Mockada
    private JobRepository jobRepository; // repositorys que estam na ApplyJobCandidateService

    @Mock //cria uma instancia de uma classe, porém Mockada
    private CandidateRepository candidateRepository; // repositorys que estam na ApplyJobCandidateService

    @Mock
    private ApplyJobRepository applyJobRepository; // repositorys que estam na ApplyJobCandidateService

    @InjectMocks  //criar uma intancia e injeta as dependências necessárias que estão anotadas com @Mock.
    private ApplyJobCandidateService applyJobCandidateService;

    @Test
    @DisplayName("Should return job not found")
    public void should_return_job_not_found(){
        // testando o primeiro erro
        try{
            this.applyJobCandidateService.execute(null, null);
        }catch (Exception e){
            assertThat(e).isInstanceOf(JobNotFound.class);
        }

    }

    @Test
    @DisplayName("Should return candidate not found")
    public void should_return_candidate_not_found(){
        // testando o segundo erro

        UUID uuid = UUID.randomUUID();

        JobsEntity jobsEntity = new JobsEntity();
        jobsEntity.setId(uuid);

        /*Pensa assim, toda vez quando(when) o jobRepository.findById(uuid) for executado com o ID gerado ele
            ele deve então retornar(thenReturn) um optional de jobs entity
        */

        when(jobRepository.findById(uuid)).thenReturn(Optional.of(jobsEntity));

        try{
            this.applyJobCandidateService.execute(uuid, null);
        }catch (Exception e){
            assertThat(e).isInstanceOf(CandidateNotFound.class);
        }
    }

    @Test
    @DisplayName("Should candidate already registered")
    public void should_candidate_already_registered(){

        // testando o terceiro erro

        UUID idCandidate = UUID.randomUUID();
        UUID idJob = UUID.randomUUID();

        CandidateEntity candidateEntity = new CandidateEntity();
        candidateEntity.setId(idCandidate);

        JobsEntity jobsEntity = new JobsEntity();
        jobsEntity.setId(idJob);

        when(candidateRepository.findById(idCandidate)).thenReturn(Optional.of(candidateEntity));
        when(jobRepository.findById(idJob)).thenReturn(Optional.of(jobsEntity));

        when(applyJobRepository.findByCandidateIdAndJobId(idCandidate, idJob)).thenReturn(Optional.of(new ApplyJobCandidateEntity()));

        try{
            this.applyJobCandidateService.execute(idJob, idCandidate);
        }catch (Exception e){
            assertThat(e).isInstanceOf(CompanyOrUserFoundException.class);
        }
    }

    @Test
    @DisplayName("Should create a object of type 'ApplyJobCandidateEntity'")
    public void should_return_object_type_applyJobCandidateEntity(){

        UUID idJob = UUID.randomUUID();

        when(jobRepository.findById(idJob)).thenReturn(Optional.of(new JobsEntity()));

        //-----------------------------------------------------------------------------

        UUID idCandidate = UUID.randomUUID();

        when(candidateRepository.findById(idCandidate)).thenReturn(Optional.of( new CandidateEntity()));

        //---------------------------------------------------------------------------------

        ApplyJobCandidateEntity applyJobCandidateEntity = new ApplyJobCandidateEntity();

        applyJobCandidateEntity.setId(UUID.randomUUID());
        applyJobCandidateEntity.setCandidateId(idCandidate);
        applyJobCandidateEntity.setJobId(idJob);

        when(applyJobRepository.save(any(ApplyJobCandidateEntity.class))).thenReturn(applyJobCandidateEntity);

        var result = this.applyJobCandidateService.execute(idJob, idCandidate);

        assertThat(result).isInstanceOf(ApplyJobCandidateEntity.class);
        assertThat(result).hasFieldOrProperty("id");
        assertNotNull(result.getId());

    }
}
