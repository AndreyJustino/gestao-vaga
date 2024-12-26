package com.andrey.rocketseat.gestao_vagas.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    private SecurityScheme securityScheme(){
        return new SecurityScheme()
                .name("JWT_Auth")
                .type(SecurityScheme.Type.HTTP)
                .scheme("bearer")
                .bearerFormat("JWT");
    }

    public OpenAPI openAPI(){
        return new OpenAPI().info(new Info()
                .title("Gestão de vagas")
                .description("Api para gerenciamento de vagas")
                .version("1")
        )
        .schemaRequirement("JWT_Auth", securityScheme());
    }

}
