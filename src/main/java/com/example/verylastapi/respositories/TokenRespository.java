package com.example.verylastapi.respositories;

import com.example.verylastapi.classes.Token;
import com.example.verylastapi.classes.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;
@Repository
public interface TokenRespository extends JpaRepository<Token,Integer> {
//addQuery

    @Query(value = "SELECT t  from Token t inner join User as u on t.user.id=u.id where u.id=:userId and (t.isRevoked=false or t.isExpired=false )")
    List<Token> findAllValidTokensFromUser(Integer userId);
    Optional<Token> findByToken(String token);
}
