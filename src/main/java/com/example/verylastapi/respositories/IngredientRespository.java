package com.example.verylastapi.respositories;
import com.example.verylastapi.classes.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IngredientRespository extends JpaRepository<Ingredient,Long>{
    List<Ingredient> findByCocktailId(Long Id);
}


