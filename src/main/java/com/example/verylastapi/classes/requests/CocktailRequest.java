package com.example.verylastapi.classes.requests;

import com.example.verylastapi.classes.Token;
import com.example.verylastapi.classes.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "CocktailRequests")
public class CocktailRequest {
    @Id
    @org.springframework.data.annotation.Id
    @GeneratedValue()
    private int id;
    private String name;
    @Column(length = 400000000)
    private String description;
    private String imageUrl;
    private String prep;
    private String tag;
    @JsonIgnore
    @ManyToOne()
    @JoinColumn(name="tokenId",nullable=false)
    private Token token;
    @JsonIgnore
    @ManyToOne()
    @JoinColumn(name="userId",nullable=false)
    private User user;
    @OneToMany(mappedBy = "cocktail")
    private List<IngredientRequest> ingredients;
    private boolean isAccepted;
    private boolean isDeleted;
    private boolean isInspected;
}
