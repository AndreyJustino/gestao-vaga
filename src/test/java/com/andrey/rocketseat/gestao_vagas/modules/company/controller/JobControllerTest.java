package com.andrey.rocketseat.gestao_vagas.modules.company.controller;

import com.andrey.rocketseat.gestao_vagas.exceptions.CompanyNotFoundException;
import com.andrey.rocketseat.gestao_vagas.modules.company.Levels;
import com.andrey.rocketseat.gestao_vagas.modules.company.dto.CreateJobDTO;

import com.andrey.rocketseat.gestao_vagas.modules.company.entities.CompanyEntity;
import com.andrey.rocketseat.gestao_vagas.modules.company.repository.CompanyRepository;
import com.andrey.rocketseat.gestao_vagas.util.TestUtil;

import org.junit.Before;
import org.junit.Test;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers;

import java.util.UUID;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@RunWith(SpringRunner.class) //Configura o ambiente de teste para usar o Spring
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test") // vai pegar o que estiver no escopo de teste. Exemplo: application-test
public class JobControllerTest {

    private MockMvc mvc; //simular requisições HTTP sem subir um servidor real

    @Autowired
    private WebApplicationContext context; // fornece o contexto da aplicação Spring para o teste

    @Autowired
    private CompanyRepository companyRepository;

    @Before //sera executado antes de cada teste
    public void setup(){
        mvc = MockMvcBuilders.webAppContextSetup(context) // usar o contexto da aplicação
                /*Aplicar o Spring Security,
                  Assim, posso simular requisições protegidas por autenticação
                  Configura o MockMvc para trabalhar com autenticação e autorização.
                 */
                .apply(SecurityMockMvcConfigurers.springSecurity())
                .build();
    }


    @Test
    public void should_be_able_to_create_a_new_job() throws Exception {
        CompanyEntity companyEntity = CompanyEntity.builder()
                .name("NAME_TEST")
                .cnpj("12.123.123/1234-12")
                .username("USERNAME_TEST")
                .email("email@mail.com")
                .password("12345678")
                .website("WEBSITE_TEST.")
                .description("DESCRIPTION_TEST")
                .build();

        companyEntity = companyRepository.saveAndFlush(companyEntity);

        CreateJobDTO createJobDTO = CreateJobDTO.builder()
                .description("VAGA_TESTE")
                .benefits("BENEFICIO_TESTE")
                .levels(Levels.JUNIOR)
                .build();

        var result = mvc.perform(
                MockMvcRequestBuilders.post("/company/jobs") //Cria uma requisição HTTP POST para o endpoint
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(TestUtil.objectToJson(createJobDTO))
                        .header("Authorization",
                                TestUtil.generateTokenCompany(companyEntity.getId())
                        )
        ).andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void should_not_be_able_to_create_a_new_job_if_company_not_found() throws Exception {
        CreateJobDTO createJobDTO = CreateJobDTO.builder()
                .description("VAGA_TESTE")
                .benefits("BENEFICIO_TESTE")
                .levels(Levels.JUNIOR)
                .build();

        mvc.perform(
                MockMvcRequestBuilders.post("/company/jobs") //Cria uma requisição HTTP POST para o endpoint
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(TestUtil.objectToJson(createJobDTO))
                        .header("Authorization",
                                TestUtil.generateTokenCompany(UUID.randomUUID())
                        )
        ).andExpect(MockMvcResultMatchers.status().isBadRequest());

    }
}
