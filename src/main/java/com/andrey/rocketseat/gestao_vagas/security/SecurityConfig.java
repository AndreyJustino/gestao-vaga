package com.andrey.rocketseat.gestao_vagas.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Configuration
@EnableMethodSecurity
public class SecurityConfig {

    @Autowired
    private SecurityCompanyFilter securityCompanyFilter;

    @Autowired
    private SecurityCandidateFilter securityCandidateFilter;

    private static final String[] PERMIT_ALL_LIST = {
            "/swagger-ui/**",
            "/v3/api-docs/**",
            "/swagger-resources/**",
            "/actuator/**"
    };

    /*esse metodo por padrão ja é gerenciado pelo spring
     mas o bean esta dizendo que agora que sobrescrever ele
     */
    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.csrf((value) -> value.disable())    //desativando a protecao de inicio do security
                .authorizeHttpRequests((auth -> {
                    auth.requestMatchers("/candidate/").permitAll() //informando que qualquer um pode acessar essa rota
                            .requestMatchers("/company/").permitAll()
                            .requestMatchers("/company/auth").permitAll()
                            .requestMatchers("/candidate/auth").permitAll()
                            .requestMatchers(PERMIT_ALL_LIST).permitAll()
                            .anyRequest().authenticated();//aqui digo que as demais precisam de autenticacao
                }))
                .addFilterBefore(securityCompanyFilter, BasicAuthenticationFilter.class)
                .addFilterBefore(securityCandidateFilter, BasicAuthenticationFilter.class)
                ;

        return httpSecurity.build();
    }

    @Bean
    PasswordEncoder passwordEncoder(){ //usado para criptografar dados
        return new BCryptPasswordEncoder();
    }
    
}
