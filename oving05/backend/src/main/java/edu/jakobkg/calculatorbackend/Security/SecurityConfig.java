package edu.jakobkg.calculatorbackend.Security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSec) throws Exception {
        return httpSec
            .csrf().disable()
            .cors().and()
            .authorizeHttpRequests()
                .requestMatchers("/token", "/history/*", "/calc").permitAll()
                .anyRequest().authenticated().and()
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
            .addFilterBefore(new AuthFilter(), UsernamePasswordAuthenticationFilter.class)
            .build();
    }
    
}
