package com.example.verylastapi.respositories;
import com.example.verylastapi.classes.Cocktail;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CocktailRespository extends JpaRepository<Cocktail,Long>
{
    @Query("select c1_0 from Cocktail c1_0 where c1_0.tag LIKE :tag")
    List<Cocktail> getByTag(@Param("tag") String tag);

@Query("select c from Cocktail c where  c.user.id=:id")
    List<Cocktail> findAllByByUser_Id(@Param("id") int id);

    Cocktail findCocktailByName(String name);
}
