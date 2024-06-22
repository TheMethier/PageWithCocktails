package com.example.verylastapi.config;
import com.example.verylastapi.classes.models.Cocktail;
import com.example.verylastapi.classes.models.Ingredient;
import com.example.verylastapi.classes.models.Token;
import com.example.verylastapi.classes.models.User;
import com.example.verylastapi.enums.Role;
import com.example.verylastapi.enums.TokenType;
import com.example.verylastapi.respositories.CocktailRespository;
import com.example.verylastapi.respositories.IngredientRespository;
import com.example.verylastapi.respositories.TokenRespository;
import com.example.verylastapi.respositories.UserRespository;
import com.example.verylastapi.services.JwtService;
import com.example.verylastapi.services.ScraperService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

@RequiredArgsConstructor
@Configuration
public class ApplicationConfiguration {
    private final   UserRespository userRespository;
    private final TokenRespository tokenRespository;
    private final JwtService service;

    @Bean
    CommandLineRunner commandLineRunner(CocktailRespository cocktailRespository, IngredientRespository ingredientRespository)
    {

        return args ->
        {
            User admin= User.builder()
                    .email("admin@admin.pl")
                    .username("admin")
                    .phoneNumber("123456789")
                    .password(passwordEncoder().encode("1234"))
                    .role(Role.ADMIN)
                    .build();
            User manager=User.builder()
                    .username("manager")
                    .email("manager@manager.pl")
                    .phoneNumber("123456789")
                    .password(passwordEncoder().encode("1234"))
                    .role(Role.ADMIN)
                    .build();
            userRespository.save(admin);
            userRespository.save(manager);
            String adminJwt= service.generateToken(admin);
            String managerJwt= service.generateToken(manager);
            Token adminToken= Token.builder()
                    .tokenType(TokenType.Bearer)
                    .token(adminJwt).user(admin)
                    .isRevoked(false)
                    .isExpired(false)
                    .build();
            Token managerToken= Token.builder()
                    .tokenType(TokenType.Bearer)
                    .token(managerJwt).user(manager)
                    .isRevoked(false)
                    .isExpired(false)
                    .build();
            tokenRespository.save(adminToken);
            tokenRespository.save(managerToken);
          ///  ScraperService scraperService= new ScraperService();
          ///  List<Cocktail> cocktails=scraperService.ScrapMyCocktail();
          ///  List<Ingredient> ingredients=scraperService.ScrapMyI(cocktails);
          ///  cocktailRespository.saveAll(cocktails);
          ///  ingredientRespository.saveAll(ingredients);
        };
    }
    @Bean
    public UserDetailsService userDetailsService()
    {
        return this::loadUserByUsername;
    }

    private UserDetails loadUserByUsername(String username) {
        return userRespository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("User Not Found"));
    }
    @Bean
    public AuthenticationProvider authenticationProvider()
    {
        DaoAuthenticationProvider authenticationProvider= new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService());
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return authenticationProvider;
    }
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }
    @Bean
    public PasswordEncoder passwordEncoder()
    {
    return new BCryptPasswordEncoder();
    }
}
