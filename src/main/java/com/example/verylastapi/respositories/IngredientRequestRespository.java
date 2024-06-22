package com.example.verylastapi.respositories;

import com.example.verylastapi.classes.models.IngredientRequest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IngredientRequestRespository extends JpaRepository<IngredientRequest,Integer> {
}
