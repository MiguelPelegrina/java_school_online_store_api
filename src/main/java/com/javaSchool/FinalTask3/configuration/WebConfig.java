package com.javaSchool.FinalTask3.configuration;

import com.javaSchool.FinalTask3.security.CustomUserDetailsService;
import com.javaSchool.FinalTask3.security.JwtAuthorizationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
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

// TODO Need to differentiate between:
//  - not logged in users (unauthorized) --> GET with Book
//  - authorized users --> POST and PUT of Order
//  - employees --> POST AND PUT of Book
//  - admin --> management of employees?
//  Not sure how much of this applies to backend and how much to frontend
// TODO Don't use deprecated methods?

/**
 * The {@code WebConfig} class is responsible for configuring various aspects of the application's web security. It defines
 * security-related beans and settings, including user authentication, authorization, CORS (Cross-Origin Resource Sharing)
 * configuration, and JWT (JSON Web Token) authorization filters.
 */
@Configuration
@EnableSpringDataWebSupport
@EnableWebSecurity
@RequiredArgsConstructor
public class WebConfig {
    // Fields
    private final CustomUserDetailsService userDetailsService;
    private final JwtAuthorizationFilter jwtAuthorizationFilter;

    /**
     * Configures and provides an {@link AuthenticationManager} that is used for user authentication and authorization.
     * It specifies the user details service and password encoder for managing user authentication.
     * @param http            The {@link HttpSecurity} configuration.
     * @param passwordEncoder The password encoder for secure authentication.
     * @return An {@link AuthenticationManager} configured for the application.
     * @throws Exception If an exception occurs during configuration.
     */
    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http, PasswordEncoder passwordEncoder)
            throws Exception {
        AuthenticationManagerBuilder authenticationManagerBuilder = http.getSharedObject(AuthenticationManagerBuilder.class);
        authenticationManagerBuilder.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
        return authenticationManagerBuilder.build();
    }

    /**
     * Provides a password encoder bean, specifically a {@link BCryptPasswordEncoder}, for securely encoding and
     * verifying passwords.
     * @return A {@link PasswordEncoder} bean.
     */
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    // TODO Not deprecated CORS -> Does not work
    /*@Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("http://localhost:4200"));
        configuration.setAllowedMethods(Arrays.asList("GET","POST"));
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }*/

    /**
     * Configures the security filter chain for the application. It specifies various security settings, including CORS,
     * (Cross-Origin Resource Sharing), CSRF (Cross-Site Request Forgery) protection, URL authorization, and JWT
     * authorization filter.
     * @param http The {@link HttpSecurity} configuration.
     * @return A {@link SecurityFilterChain} for the application's security.
     * @throws Exception If an exception occurs during configuration.
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        // TODO Try some way that is not deprecated?
        return http.cors().and()
                // TODO Not deprecated CORS -> Does not work
                // .cors(Customizer.withDefaults())
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/auth/login",
                                "/api-docs",
                                "/api-docs.yaml").permitAll()
                        .anyRequest().authenticated()
                )
                .sessionManagement(sess -> sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                . addFilterBefore(jwtAuthorizationFilter, UsernamePasswordAuthenticationFilter.class)
                .httpBasic(Customizer.withDefaults())
                .build();
    }

    /**
     * Configures CORS settings for the application, allowing cross-origin requests from the specified origin
     * (http://localhost:4200).
     * @return A {@link WebMvcConfigurer} for configuring CORS settings.
     */
    @Bean
    public WebMvcConfigurer corsConfig() {
        // TODO Not sure if right, might be missing something
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                        .allowedOrigins("http://localhost:4200");
            }
        };
    }
}

