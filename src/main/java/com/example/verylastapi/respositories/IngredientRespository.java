package com.example.verylastapi.respositories;
import com.example.verylastapi.classes.Ingredients;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IngredientRespository extends JpaRepository<Ingredients,Long>{
    List<Ingredients> findByCocktailId(Long Id);
}


