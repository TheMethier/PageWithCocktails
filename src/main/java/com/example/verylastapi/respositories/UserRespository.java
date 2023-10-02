package com.example.verylastapi.respositories;

import com.example.verylastapi.classes.User;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface UserRespository extends JpaRepository<User, Integer> {
    Optional<User> findByUsername( String username);
}
