package com.example.verylastapi.classes.requests;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@AllArgsConstructor
@Builder
@Data
public class IngredientAdditionRequest {
    @NotBlank(message = "name shouldn't be blank")
    @NotNull(message = "name shouldn't be null")
    private String name;
    @Digits(fraction = 2,message = "quantity should be a number", integer = 3)
    @NotBlank(message = "quantity shouldn't be blank")

    @NotNull(message = "quantity shouldn't be null")
    private Float quantity;
    @NotNull(message = "unit shouldn't be null")
    @NotBlank(message = "unit shouldn't be blank")
    private String unit;
}
