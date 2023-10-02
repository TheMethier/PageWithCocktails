package com.example.verylastapi.classes;
import jakarta.persistence.*;
import org.springframework.data.annotation.Id;
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
    private String prep;
    private String tag;
    //Dodaj Size
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
    public Cocktail(int id, String name, String description,String prep)
    {
        this.id=id;
        this.description=description;
        this.name=name;
        this.prep=prep;
    }

    public Cocktail(String name, String description, String imageUrl, Set<Ingredients> ingredients, String prep, String tag)
    {
        this.tag=tag;
        this.prep=prep;
        this.imageUrl=imageUrl;
        this.description=description;
        this.name=name;}

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
}
