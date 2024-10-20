package com.project.uber.uberApp.configs;

import com.project.uber.uberApp.security.JWTAuthFilter;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@EnableMethodSecurity(securedEnabled = true)
public class WebSecurityConfig {

    private final JWTAuthFilter jwtAuthFilter;
    private final String[] PUBLIC_ROUTES = {"/auth/**"};

    // Add an array for Swagger-related endpoints
    private final String[] SWAGGER_ROUTES = {
            "/swagger-ui/**",
            "/v3/api-docs/**",
            "/swagger-resources/**",
            "/webjars/**"
    };

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {

        httpSecurity
                .sessionManagement(sessionConfig -> sessionConfig
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .csrf(csrfConfig -> csrfConfig.disable())
                .formLogin(formLogin -> formLogin.disable())
                .authorizeHttpRequests(auth -> auth
                        // Permit all for public routes and Swagger routes
                        .requestMatchers(PUBLIC_ROUTES).permitAll()
                        .requestMatchers(SWAGGER_ROUTES).permitAll()
                        .anyRequest().authenticated()
                )
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);

        return httpSecurity.build();
    }

    // Add Swagger configuration for production server URL
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info().title("Uber App API").version("v1"))
                .addServersItem(new Server().url("https://uberapp-production.up.railway.app"));
    }
}
