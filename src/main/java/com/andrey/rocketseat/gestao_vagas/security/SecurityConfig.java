package com.andrey.rocketseat.gestao_vagas.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    /*esse metodo por padrão ja é gerenciado pelo spring
     mas o bean esta dizendo que agora que sobrescrever ele
     */
    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.csrf((value) -> value.disable()) //desativando a protecao de inicio do security
                .authorizeHttpRequests((auth -> {
                    auth.requestMatchers("/candidate/").permitAll() //informando que qualquer um pode acessar essa rota
                            .requestMatchers("/company/").permitAll()
                            .requestMatchers("/auth/company").permitAll()
                            .anyRequest().authenticated();//aqui digo que as demais precisam de autenticacao
                }));

        return httpSecurity.build();
    }

    @Bean
    PasswordEncoder passwordEncoder(){ //usado para criptografar dados
        return new BCryptPasswordEncoder();
    }
    
}
