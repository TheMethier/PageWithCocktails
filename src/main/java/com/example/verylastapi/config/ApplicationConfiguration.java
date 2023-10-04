package com.example.verylastapi.config;

import com.example.verylastapi.classes.Cocktail;
import com.example.verylastapi.classes.Ingredient;
import com.example.verylastapi.services.ScraperService;
import com.example.verylastapi.respositories.CocktailRespository;
import com.example.verylastapi.respositories.IngredientRespository;
import com.example.verylastapi.respositories.UserRespository;
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
    private final   UserRespository respository;


    @Bean
    CommandLineRunner commandLineRunner(CocktailRespository cocktailRespository, IngredientRespository ingredientRespository)
    {
        return args -> {
            ScraperService scraperController= new ScraperService();
           /*Set <Ingredients> p =new HashSet<>();
            Cocktail w=new Cocktail("y","t",p);
            p.add(new Ingredients(w,"n", 15F,"ml"));
            cocktailRespository.saveAll(List.of(w));
*/
                List<Cocktail> cocktails=scraperController.ScrapMyCocktail();
                List<Ingredient> ingredients=scraperController.ScrapMyI(cocktails);
                cocktailRespository.saveAll(cocktails);
                ingredientRespository.saveAll(ingredients);

        };
    }
    @Bean
    public UserDetailsService userDetailsService()
    {
        return this::loadUserByUsername;
    }

    private UserDetails loadUserByUsername(String username) {
        return respository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("User Not Found"));
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
