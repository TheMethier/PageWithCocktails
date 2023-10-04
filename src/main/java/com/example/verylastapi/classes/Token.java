package com.example.verylastapi.classes;

import com.example.verylastapi.enums.TokenType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Tokens")

public class Token {
    @GeneratedValue
    @Id
    @org.springframework.data.annotation.Id
    private int id;
    private String token;
    @Enumerated
    private TokenType tokenType;

    private boolean isExpired;

    private boolean isRevoked;

    @ManyToOne()
    @JoinColumn(name = "UserId",nullable = false)
    private User user;


}
