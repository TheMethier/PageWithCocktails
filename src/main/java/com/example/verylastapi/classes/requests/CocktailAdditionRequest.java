package com.example.verylastapi.classes.requests;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class CocktailAdditionRequest {
    @NotNull(message = "field shouldn't be null")
    @NotBlank(message = "field shouldn't be blank")
    private String name;
    @NotNull(message = "field shouldn't be null")
    @NotBlank(message = "field shouldn't be blank")
    private String description;
    @NotNull(message = "field shouldn't be null")
    @NotBlank(message = "field shouldn't be blank")
    private String imageUrl;
    @NotNull(message = "field shouldn't be null")
    @NotBlank(message = "field shouldn't be blank")
    private String prep;
    private String tag;
}
