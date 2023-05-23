package com.example.verylastapi.respositories;
import com.example.verylastapi.classes.Cocktail;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

@Repository
public interface CocktailRespository extends JpaRepository<Cocktail,Long>
{

}
