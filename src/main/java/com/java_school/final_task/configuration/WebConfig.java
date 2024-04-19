package com.java_school.final_task.configuration;

import com.java_school.final_task.security.CustomUserDetailsService;
import com.java_school.final_task.security.JwtAuthorizationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * The {@code WebConfig} class is responsible for configuring various aspects of the application's web security. It
 * defines security-related beans and settings, including user authentication, authorization, CORS (Cross-Origin
 * Resource Sharing) configuration, and JWT (JSON Web Token) authorization filters.
 */
@Configuration
@EnableSpringDataWebSupport
@EnableWebSecurity
@RequiredArgsConstructor
public class WebConfig {
    /**
     * Configures and provides an {@link AuthenticationManager} that is used for user authentication and authorization.
     * It specifies the user details service and password encoder for managing user authentication.
     *
     * @param userDetailsService The {@link CustomUserDetailsService} for retrieving user details during authentication.
     * @param http               The {@link HttpSecurity} configuration.
     * @param passwordEncoder    The {@link PasswordEncoder} for secure password encoding during authentication.
     * @return {@link AuthenticationManager} configured for the application.
     * @throws Exception If an exception occurs during configuration.
     */
    @Bean
    public AuthenticationManager authenticationManager(
            CustomUserDetailsService userDetailsService,
            HttpSecurity http,
            PasswordEncoder passwordEncoder
    ) throws Exception {
        AuthenticationManagerBuilder authenticationManagerBuilder = http.getSharedObject(AuthenticationManagerBuilder.class);

        authenticationManagerBuilder.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);

        return authenticationManagerBuilder.build();
    }

    /**
     * Provides a password encoder bean, specifically a {@link BCryptPasswordEncoder}, for securely encoding and
     * verifying passwords.
     *
     * @return {@link PasswordEncoder} bean.
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * Configures the security filter chain for the application. It specifies various security settings, including CORS,
     * (Cross-Origin Resource Sharing), CSRF (Cross-Site Request Forgery) protection, URL authorization, and JWT
     * authorization filter.
     *
     * @param http                   The {@link HttpSecurity} configuration.
     * @param jwtAuthorizationFilter The {@link JwtAuthorizationFilter} responsible for JWT-based authorization.
     * @return {@link SecurityFilterChain} for the application's security.
     * @throws Exception If an exception occurs during configuration.
     */
    @Bean
    public SecurityFilterChain securityFilterChain(
            HttpSecurity http,
            JwtAuthorizationFilter jwtAuthorizationFilter
    ) throws Exception {
        return http.cors().and()
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(HttpMethod.GET, "/book_genres", "/books/{id}").permitAll()
                        .requestMatchers(
                                "/auth/login",
                                "/auth/register",
                                "/api-docs",
                                "/api-docs.yaml",
                                "/books/search",
                                "/countries/search",
                                "/cities/search",
                                "/delivery_methods/search",
                                "/order_statuses/search",
                                "/payment_methods/search",
                                "/payment_statuses/search",
                                "/postal_codes/search",
                                "/swagger-ui/**",
                                "/api-docs/swagger-config"
                        ).permitAll()
                        .anyRequest().authenticated()
                )
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterBefore(jwtAuthorizationFilter, UsernamePasswordAuthenticationFilter.class)
                .httpBasic(Customizer.withDefaults())
                .build();
    }

    /**
     * Configures CORS settings for the application, allowing cross-origin requests from the specified origin
     * (<a href="http://localhost:4200">...</a>).
     *
     * @return {@link WebMvcConfigurer} for configuring CORS settings.
     */
    @Bean
    public WebMvcConfigurer corsConfig() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                        .allowedOrigins("http://localhost:4200", "http://127.0.0.1:4200");
            }
        };
    }
}

