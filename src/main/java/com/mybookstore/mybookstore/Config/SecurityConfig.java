package com.mybookstore.mybookstore.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain; 

import lombok.AllArgsConstructor;

@Configuration
@EnableWebSecurity 
@AllArgsConstructor
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
            .csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(authRequests -> authRequests
                .requestMatchers("/auth/**").permitAll() 
                .anyRequest().authenticated() 
            )
            .formLogin(Customizer.withDefaults()) 
            .build();
    }
}
  