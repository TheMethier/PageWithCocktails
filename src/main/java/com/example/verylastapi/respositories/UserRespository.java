package com.example.verylastapi.respositories;

import com.example.verylastapi.classes.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface UserRespository extends JpaRepository<User, Integer> {
    Optional<User> findByUsername(String username);
}
