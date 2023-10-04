package com.example.verylastapi.respositories;

import com.example.verylastapi.classes.requests.CocktailRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CocktailRequestRespository extends JpaRepository<CocktailRequest,Integer> {

}
