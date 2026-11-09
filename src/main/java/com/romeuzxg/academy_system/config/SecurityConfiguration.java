package com.romeuzxg.academy_system.config;

import com.romeuzxg.academy_system.exceptions.AccessDeniedHandler;
import com.romeuzxg.academy_system.exceptions.MyEntryPoint;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration {

    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        return http.exceptionHandling(exceptions -> exceptions
                        .authenticationEntryPoint(new MyEntryPoint())
                        .accessDeniedHandler(new AccessDeniedHandler()))
                .csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .exceptionHandling(ex -> ex
                        .authenticationEntryPoint(new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED))
                        .accessDeniedHandler((request, response, accessDeniedException) ->
                        {
                            response.setStatus(HttpStatus.FORBIDDEN.value());
                        }))
                .authorizeHttpRequests(auth -> auth

                        .requestMatchers("/v1/auth/**").permitAll()

                        .requestMatchers(HttpMethod.POST, "/v1/auth/register", "/v1/auth/login").permitAll()

                        .requestMatchers(HttpMethod.GET, "/turmas", "/turmas/{id}", "/instrutores", "/instrutores/{id}").hasAnyRole("ALUNO", "ADMIN")

                        .requestMatchers(HttpMethod.POST, "/turmas", "/instrutores").hasRole("ADMIN")

                        .requestMatchers(HttpMethod.PUT, "/turmas/{id}", "/instrutores/{id}").hasRole("ADMIN")

                        .requestMatchers(HttpMethod.DELETE, "/turmas/{id}", "/instrutores/{id}").hasRole("ADMIN")

                        .requestMatchers("/alunos", "/alunos/**").hasRole("ADMIN")

                        .requestMatchers("/matriculas", "/matriculas/**").hasRole("ADMIN")

                        .anyRequest().authenticated()
                )
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) {
        return authenticationConfiguration.getAuthenticationManager();
    }


}
