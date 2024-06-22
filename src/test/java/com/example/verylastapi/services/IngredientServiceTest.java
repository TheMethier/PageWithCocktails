package com.example.verylastapi.services;

import com.example.verylastapi.classes.requests.IngredientAdditionRequest;
import com.example.verylastapi.respositories.CocktailRespository;
import com.example.verylastapi.respositories.IngredientRespository;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.NoSuchElementException;

@ExtendWith(MockitoExtension.class)

class IngredientServiceTest {

    @Mock
    private IngredientRespository ingredientRespository;
    @Mock
    private CocktailRespository cocktailRespository;
    @InjectMocks
    private IngredientService ingredientService;
    @BeforeEach
    void setUp() {
        ingredientRespository = Mockito.mock(IngredientRespository.class);
        MockitoAnnotations.openMocks(this);

    }


    @Test
    void addNewIngredients_ThrowException_WhenCocktailInGivenIdIsNull() {
        //given
        Long id= -1L;
        IngredientAdditionRequest ingredient = new IngredientAdditionRequest("name",6F,"ml");
        //when and then
        Assert.assertThrows(NoSuchElementException.class,
                ()->ingredientService.addNewIngredient(ingredient,id));
    }


}