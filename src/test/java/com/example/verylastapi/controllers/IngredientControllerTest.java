package com.example.verylastapi.controllers;

import com.example.verylastapi.classes.models.Cocktail;
import com.example.verylastapi.classes.models.Ingredient;
import com.example.verylastapi.classes.requests.IngredientAdditionRequest;
import com.example.verylastapi.services.IngredientService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@AutoConfigureMockMvc
@SpringBootTest
@RunWith(SpringRunner.class)
class IngredientControllerTest {
    @Autowired
    private MockMvc mvc;
    @Autowired
    private ObjectMapper objectMapper;
    @MockBean
    private IngredientService service;

    @BeforeEach
    void setUp() {
    }

    @Test
    void getIngredientById_ShouldReturn204Status_WhenIngredientDBIsEmpty() throws Exception {
        //given
        int expected=204;
        List<Ingredient> ingredientList = new ArrayList<>();
        given(service.GetAllIngredients(-1L)).willReturn(List.of());
        //when
        var response = mvc.perform(get("/api/v1/indi/-1").accept(MediaType.APPLICATION_JSON)).andReturn().getResponse();
        Assert.assertEquals(expected,response.getStatus());
    }
    @Test
    void getIngredientById_ShouldReturn200Status_WhenCocktailWasFound() throws Exception {
        //given
        int expected=200;
        List<Ingredient> ingredientList = new ArrayList<>();
        given(service.GetAllIngredients(1L)).willReturn(List.of(
                Ingredient.builder()
                        .id(1).unit("ml")
                        .cocktail(Cocktail.builder().id(1).build())
                        .name("water")
                        .quantity(100f)
                        .build())
        );
        //when
        var response = mvc.perform(get("/api/v1/indi/1").accept(MediaType.APPLICATION_JSON)).andReturn().getResponse();
        Assert.assertEquals(expected,response.getStatus());
    }
    @Test
    void getIngredientById_ShouldReturnList() throws Exception {
        //given
        List<Ingredient> ingredientList=List.of(
                Ingredient.builder()
                        .id(1).unit("ml")
                        .cocktail(Cocktail.builder().id(1).build())
                        .name("water")
                        .quantity(100f)
                        .build());
        String expected= objectMapper.writeValueAsString(ingredientList);
        given(service.GetAllIngredients(1L)).willReturn(ingredientList);
        //when
        var response = mvc.perform(get("/api/v1/indi/1").accept(MediaType.APPLICATION_JSON)).andReturn().getResponse();
        //then
        Assert.assertEquals(expected,response.getContentAsString());
    }
    @Test
    void addNewIngredients_ShouldReturnCreatedStatus_WhenAdditionWereSuccessful() throws Exception {
        //given
        Mockito.doNothing()
                .when(this.service)
                .addNewIngredient(IngredientAdditionRequest.builder().build(),1L);
        int expected=201;
        IngredientAdditionRequest ingredient = IngredientAdditionRequest.builder()
                .name("name").quantity(5f).unit("ml")
                .build();
        String body = objectMapper.writeValueAsString(ingredient);
        //when
        var response=mvc.perform(post("/api/v1/indi/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(body))
                .andReturn()
                .getResponse();
        //then
        Assert.assertEquals(expected,response.getStatus());
    }
}