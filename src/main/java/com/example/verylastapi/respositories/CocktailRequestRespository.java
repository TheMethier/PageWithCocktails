package com.example.verylastapi.respositories;

import com.example.verylastapi.classes.requests.CocktailRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CocktailRequestRespository extends JpaRepository<CocktailRequest,Integer> {
    List<CocktailRequest> findAllByUser_Id(int userId);
    @Query("SELECT c from CocktailRequest c where (c.isAccepted=false) and (c.isInspected=false)")
    List<CocktailRequest> findAllWaitingRequests();
}
