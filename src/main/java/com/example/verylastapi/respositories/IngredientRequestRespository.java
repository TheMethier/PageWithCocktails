package com.example.verylastapi.respositories;

import com.example.verylastapi.classes.requests.IngredientRequest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IngredientRequestRespository extends JpaRepository<IngredientRequest,Integer> {
}
