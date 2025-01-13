package com.andrey.rocketseat.gestao_vagas.security;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.andrey.rocketseat.gestao_vagas.providers.JWTCompanyProviders;
import com.auth0.jwt.interfaces.DecodedJWT;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class SecurityCompanyFilter extends OncePerRequestFilter{

    @Autowired
    private JWTCompanyProviders jwtProviders;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

                String header = request.getHeader("Authorization");


                if(request.getRequestURI().startsWith("/company")){

                    if(header != null){

                        DecodedJWT token = this.jwtProviders.validadeToken(header);
                        if (token == null) {

                            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                            return;
                        }
    
                        request.setAttribute("company_id", token.getSubject());

                        List<Object> roles = token.getClaim("roles").asList(Object.class);

                        List<SimpleGrantedAuthority> grants = roles.stream()
                        .map((value) -> {
                            return new SimpleGrantedAuthority("ROLE_" + value.toString().toUpperCase());
                        }).toList();

                        UsernamePasswordAuthenticationToken auth = 
                            new UsernamePasswordAuthenticationToken(token.getSubject(), null, grants);
    
                        SecurityContextHolder.getContext().setAuthentication(auth);
                    }
                }

                filterChain.doFilter(request, response);
    }
    
}
