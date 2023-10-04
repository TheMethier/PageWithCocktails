package com.example.verylastapi.classes;

import jakarta.persistence.*;
import org.springframework.data.annotation.Id;
@Entity
@Table(name="Ingredients")
public class Ingredient {
    @jakarta.persistence.Id
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne()
    @JoinColumn(name="CocktailId",nullable=false)
    private Cocktail cocktail;
    private String name;
    private Float quantity;
    private String unit;
public Ingredient()
{

}
    public Ingredient(Cocktail cocktail, String name, Float quantity, String unit) {
        this.cocktail=cocktail;
        this.name=name;
        this.unit=unit;
        this.quantity=quantity;
    }

    public Ingredient(int cockid , String name, Float quantity, String unit) {
        this.cocktail.setId(cockid);
        this.name=name;
        this.unit=unit;
        this.quantity=quantity;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }



    public Float getQuantity() {
        return quantity;
    }

    public void setQuantity(Float quantity) {
        this.quantity = quantity;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return this.id;
    }


    public Cocktail getCocktail() {
        return cocktail;
    }

    public void setCocktail(Cocktail cocktail) {
        this.cocktail = cocktail;
    }
}
