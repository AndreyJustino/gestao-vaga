package com.andrey.rocketseat.gestao_vagas;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(
		info = @Info(
				title = "Gestão de vagas",
				description = "Api para gerenciamento de vagas",
				version = "1"
		)
)
@SecurityScheme(name = "JWT_Auth", scheme = "bearer", bearerFormat = "JWT",
		type = SecuritySchemeType.HTTP, in = SecuritySchemeIn.HEADER
)//dizendo pro swagger como é feito a autenticacao em algumas rotas
public class GestaoDeVagasApplication {

	public static void main(String[] args) {
		SpringApplication.run(GestaoDeVagasApplication.class, args);
	}

}
