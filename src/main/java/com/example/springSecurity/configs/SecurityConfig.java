package com.example.springSecurity.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.example.springSecurity.repositories.UserRepository;

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig {

    private final UserRepository userRepository;
    
    @Bean
    public UserDetailsService userDetailsService(){
        return username -> userRepository.findByUsername(username);
    }

    @Bean
    public AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService());
        provider.setPasswordEncoder(passwordEncoder());
        return provider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception{
        return authenticationConfiguration.getAuthenticationManager();
    }



    // @Bean
    // public UserDetailsManager userDetailsManager(){
    //     InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
    //     manager.createUser(
    //         User.withUsername("nai1").password(passwordEncoder().encode("nai1")).roles("USER").build()           
    //     );
    //     manager.createUser(
    //         User.withUsername("nai2").password(passwordEncoder().encode("nai2")).roles("ADMIN").build()           
    //     );
    //     return manager;
    // }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        http.csrf().disable()
        .authorizeRequests()
        .requestMatchers(HttpMethod.GET,"/api/user/login").hasRole("ADMIN")
        .requestMatchers(HttpMethod.GET,"/api/user/list/**").hasRole("USER")
        .requestMatchers(HttpMethod.GET,"/api/category/**").permitAll()
        .requestMatchers(HttpMethod.POST,"/api/user/register").permitAll()
        .anyRequest().authenticated()
        .and()
        .httpBasic()
        .and()
        .formLogin().disable();
        return http.build();
    }
}
