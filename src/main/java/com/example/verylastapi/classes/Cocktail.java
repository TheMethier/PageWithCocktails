package com.example.verylastapi.classes;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;
import org.springframework.data.annotation.Id;

import java.time.LocalDate;
import java.time.Period;

@Entity
@Table
public class Cocktail
{

    @jakarta.persistence.Id
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "student_sequence"
    )
    private Long id;
    private String name;
    private String description;
    private String ingredients;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getIngredients() {
        return ingredients;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }

    public Cocktail() {
    }
    public Cocktail(Long id, String name, String description, String ingredients)
    {
        this.id=id;
        this.description=description;
        this.ingredients=ingredients;
        this.name=name;
    }

    public Cocktail(String name, String description, String ingredients)
    {
        this.description=description;
        this.ingredients=ingredients;
        this.name=name;}


};
