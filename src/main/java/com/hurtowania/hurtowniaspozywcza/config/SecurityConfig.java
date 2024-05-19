package com.hurtowania.hurtowniaspozywcza.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.hurtowania.hurtowniaspozywcza.AppUser.UserType;
import com.hurtowania.hurtowniaspozywcza.auth.AppUserDetailsService;
import com.hurtowania.hurtowniaspozywcza.auth.AuthEntryPoint;
import com.hurtowania.hurtowniaspozywcza.auth.AuthTokenFilter;

import static org.springframework.security.config.Customizer.withDefaults;

import org.springframework.beans.factory.annotation.Autowired;

@Configuration
public class SecurityConfig {

    @Autowired
    AuthEntryPoint unauthorizedHandler;

    @Autowired
    AppUserDetailsService detailsService;

    @Bean
    public AuthTokenFilter authenticationJwtTokenFilter() {
        return new AuthTokenFilter();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(detailsService);
        authProvider.setPasswordEncoder(new BCryptPasswordEncoder());
        return authProvider;
    }
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }
    @Bean
    public static PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable)
                .exceptionHandling(exception -> exception.authenticationEntryPoint(unauthorizedHandler))
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests((authorize) -> authorize
                        .requestMatchers(HttpMethod.PUT, "/product/**/archive").hasAnyAuthority("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/product/**").hasAnyAuthority("ADMIN", "EMPLOYEE")
                        .requestMatchers(HttpMethod.POST, "/product/**").hasAnyAuthority("ADMIN", "EMPLOYEE")
                        .requestMatchers("/product/**").hasAnyAuthority("CLIENT", "ADMIN", "EMPLOYEE")
                        
                        .requestMatchers(HttpMethod.PUT, "/order/**").hasAnyAuthority("ADMIN", "EMPLOYEE")
                        .requestMatchers("/order/**").hasAnyAuthority("CLIENT", "ADMIN", "EMPLOYEE")
                        
                        .requestMatchers("/api/protected/**").authenticated()
                        .anyRequest().permitAll()
        );

        http.authenticationProvider(authenticationProvider());
        http.addFilterBefore(authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

}

