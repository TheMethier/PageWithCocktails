package com.example.verylastapi.classes;
import jakarta.persistence.*;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.util.List;
import java.util.Set;

@Entity
@NoArgsConstructor
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
    private String prep;
    private String tag;
    @ManyToOne()
    private User user;

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

    public Cocktail(User user) {
        this.user = user;
    }
    public Cocktail(int id, String name, String description,String prep)
    {
        this.id=id;
        this.description=description;
        this.name=name;
        this.prep=prep;
    }

    public Cocktail(String name, String description, String imageUrl, Set<Ingredient> ingredients, String prep, String tag)
    {
        this.tag=tag;
        this.prep=prep;
        this.imageUrl=imageUrl;
        this.description=description;
        this.name=name;}
    public Cocktail(String name, String description, String imageUrl, Set<Ingredient> ingredients, String prep, String tag,User user)
    {
        this.tag=tag;
        this.prep=prep;
        this.imageUrl=imageUrl;
        this.description=description;
        this.name=name;
        this.user=user;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getPrep() {
        return prep;
    }

    public void setPrep(String prep) {
        this.prep = prep;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }


}
