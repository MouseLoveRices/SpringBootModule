package com.example.ecommerce.Backend.conifgs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
public class SecurityConfig {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf().disable()
            .authorizeRequests()
            .requestMatchers("/api/users/register").permitAll()
            .requestMatchers("/api/users/all").hasRole("CUSTOMER")
            .anyRequest().authenticated()
            .and()
            .formLogin().disable()
            .httpBasic();
        return http.build();
}
}
