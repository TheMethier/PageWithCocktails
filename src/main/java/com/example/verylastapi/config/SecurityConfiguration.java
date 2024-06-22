package com.example.verylastapi.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutHandler;

@EnableWebSecurity
@Configuration
@RequiredArgsConstructor
public class SecurityConfiguration {
 private final AuthenticationProvider authenticationProvider;
 private final JwtAuthenticationFilter jwtAuthenticationFilter;
private final LogoutHandler logoutHandler;
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception
    {
        http.csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(x->x.requestMatchers("/api/v1/auth/register","" +
                        "/doc/swagger-ui.html","/api/v1/auth/authenticate","/swagger-ui/**","" +
                        "/v3/api-docs/**","/api-docs/**","/api/v1/indi/**","/api/v1/cocktails/**",
                                "/api/v1/cocktails",
                                "/api/v1/cocktails/cocktail/**",
                                "/v3/api-docs/**",
                                "/v3/api-docs/",
                                "/v3/**",
                                "/v2/**",
                                "/swagger/**","/v3/**","api/app",
                                "/swagger-ui/**","/v1/api/**",
                                "/v2/api-docs/**",
                                "/swagger-resources/**"
                        ,"/api/v1/indi/**"
                                )
                        .permitAll().anyRequest().authenticated())
                .sessionManagement(x->x.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationProvider(authenticationProvider).addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .logout(x->{
                    x.addLogoutHandler(logoutHandler);
                    x.logoutUrl("/api/v1/auth/logout");//it generate logout endpoint
                    x.logoutSuccessHandler((request, response, authentication)-> {// when logout is successful, we clean a ContextHolder
                        SecurityContextHolder.clearContext();
                    });
                })
                ;
        return http.build();
    }
}
