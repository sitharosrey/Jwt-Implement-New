package com.example.demo_jwt_1.config;

import com.example.demo_jwt_1.service.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfiguration {

    private final UserService userService;
    private final PasswordEncoderConfig passwordEncoderConfig;

    public SecurityConfiguration(UserService userService, PasswordEncoderConfig passwordEncoderConfig) {
        this.userService = userService;
        this.passwordEncoderConfig = passwordEncoderConfig;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }

    @Bean
    public DaoAuthenticationProvider provider(){
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userService);
        provider.setPasswordEncoder(passwordEncoderConfig.passwordEncoder());
        return provider;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeHttpRequests()
                .requestMatchers("/login").permitAll()
                .requestMatchers("/authenticate").permitAll()
                .anyRequest().permitAll()
                .and()
                .httpBasic();
        return http.build();
    }
}
