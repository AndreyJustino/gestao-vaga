package com.andrey.rocketseat.gestao_vagas.modules.company.controller;

import com.andrey.rocketseat.gestao_vagas.modules.company.Levels;
import com.andrey.rocketseat.gestao_vagas.modules.company.dto.CreateJobDTO;

import com.andrey.rocketseat.gestao_vagas.util.TestUtil;

import org.junit.Before;
import org.junit.Test;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers;

import java.util.UUID;

@RunWith(SpringRunner.class) //Configura o ambiente de teste para usar o Spring
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class JobControllerTest {

    private MockMvc mvc; //simular requisições HTTP sem subir um servidor real

    @Autowired
    private WebApplicationContext context; // fornece o contexto da aplicação Spring para o teste

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
        CreateJobDTO createJobDTO = CreateJobDTO.builder()
                .description("Vaga de teste")
                .benefits("Beneficio de teste")
                .levels(Levels.JUNIOR)
                .build();

        var result = mvc.perform(
                MockMvcRequestBuilders.post("/company/jobs") //Cria uma requisição HTTP POST para o endpoint
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(TestUtil.objectToJson(createJobDTO))
                        .header("Authorization",
                                TestUtil.generateTokenCompany(UUID.randomUUID())
                        )
        ).andExpect(MockMvcResultMatchers.status().isOk());

        System.out.println(result);
    }
}
