package com.example.verylastapi.classes;
import jakarta.persistence.*;
import org.hibernate.annotations.Columns;
import org.springframework.data.annotation.Id;

import java.time.LocalDate;
import java.time.Period;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="Cocktails")
public class Cocktail
{

    @jakarta.persistence.Id
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE
    )
    private int id;
    private String name;
    @Column(length = 400000000)
    private String description;
    private String imageUrl;
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Cocktail() {
    }
    public Cocktail(int id, String name, String description)
    {
        this.id=id;
        this.description=description;
        this.name=name;
    }

    public Cocktail(String name, String description, String imageUrl, Set<Ingredients> ingredients)
    {
        this.imageUrl=imageUrl;
        this.description=description;
        this.name=name;}

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
};
