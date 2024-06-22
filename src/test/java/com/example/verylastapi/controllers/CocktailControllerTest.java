package com.example.verylastapi.controllers;

import com.example.verylastapi.classes.models.Cocktail;
import com.example.verylastapi.classes.requests.CocktailAdditionRequest;
import com.example.verylastapi.services.CocktailService;
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
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
@AutoConfigureMockMvc
@SpringBootTest
@RunWith(SpringRunner.class)
class CocktailControllerTest {

    @Autowired
    private MockMvc mvc;
    @Autowired
    private ObjectMapper objectMapper;
    @MockBean
    private CocktailService service;
    @BeforeEach
    void setUp() {
    }

    @Test
    void getCocktails_shouldReturnStatus200() throws Exception {
       //given
        Cocktail cocktail = new Cocktail();
        List<Cocktail> allCocktails = Arrays.asList(cocktail);
        given(service.getCocktails()).willReturn(allCocktails);
        //when
        MockHttpServletResponse response = mvc.perform(get("/api/v1/cocktails/cocktail/").accept(MediaType.APPLICATION_JSON))
                .andReturn()
                .getResponse();
        //then
        Assert.assertEquals(response.getStatus(),200);
    }
    @Test
    void getCocktails_shouldReturnCorrectList() throws Exception {
        //given
        Cocktail cocktail = new Cocktail();
        List<Cocktail> allCocktails = Arrays.asList(cocktail);
        given(service.getCocktails()).willReturn(allCocktails);
        //when
        MockHttpServletResponse response = mvc.perform(get("/api/v1/cocktails/cocktail/").accept(MediaType.APPLICATION_JSON))
                .andReturn()
                .getResponse();
        //then
        Assert.assertEquals(objectMapper.writeValueAsString(allCocktails),response.getContentAsString() );
    }
    @Test
    void getCocktails_shouldReturnStatus204_WhenListIsEmpty() throws Exception {
        //given
        List<Cocktail> allCocktails = Arrays.asList();
        given(service.getCocktails()).willReturn(allCocktails);
        //when
        var response = this.mvc.perform(get("/api/v1/cocktails/cocktail/")
                        .contentType(MediaType.APPLICATION_JSON))
               .andReturn()
               .getResponse();
        //then
       Assert.assertEquals(response.getStatus(),204);
    }
    @Test
    void getCocktailById_shouldReturnStatus200_WhenCocktailExistsInDB() throws Exception {
        //given
        Cocktail cocktail = Cocktail.builder()
                .id(2).
                description("rrr")
                .prep("ooo")
                .name("mohito")
                .imageUrl("localhost").build();
        given(service.getCocktail(2L)).willReturn(
                cocktail
        );
        //when
        var response=this.mvc.perform(get("/api/v1/cocktails/2")
                        .contentType(MediaType.APPLICATION_JSON))
                .andReturn()
                .getResponse();
        //then
        Assert.assertEquals(response.getStatus(),200);
    }
    @Test
    void getCocktailById_shouldReturnStatus404_WhenCocktailDoesntExistsInDB() throws Exception {
        //given
        Cocktail cocktail = Cocktail.builder()
               .id(2).
               description("rrr")
               .prep("ooo")
               .name("mohito")
               .imageUrl("localhost").build();
        given(service.getCocktail(2L)).willReturn(cocktail);
        //when
        var response = this.mvc.perform(get("/api/v1/cocktails/90"))
              .andReturn()
              .getResponse();
        //then
        Assert.assertEquals(response.getStatus(),404);
    }
    @Test
    void getCocktailById_shouldReturnCorrectCocktail_WhenCocktailExistsInDB() throws Exception {
        //given
        Cocktail cocktail=Cocktail.builder()
                .id(1)
                .description("rrr")
                .prep("ooo")
                .name("mohito")
                .imageUrl("localhost")
                .build();
        given(service.getCocktail(1L)).willReturn(cocktail);
        //when
        var response =this.mvc.perform(get("/api/v1/cocktails/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();
        //then
        Assert.assertEquals(response.getContentAsString(),objectMapper.writeValueAsString(cocktail));
    }

    @Test
    void addNewCocktail_shouldReturnCreated_WhenItemWasCreated() throws Exception {
        //given
        Mockito.doNothing()
                .when(this.service)
                .addNewCocktail(Mockito.any(CocktailAdditionRequest.class));
        CocktailAdditionRequest cocktail = CocktailAdditionRequest.builder()
                .name("mohit0")
                .description("rrrrr")
                .imageUrl("rrrrr")
                .prep("rrr")
                .build();
        String body = new ObjectMapper().writeValueAsString(cocktail);
        //when
        var response = this.mvc.perform(post("/api/v1/cocktails/")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .characterEncoding(StandardCharsets.UTF_8)
                .content(body)).andReturn().getResponse();
        //then
        Assert.assertEquals(response.getStatus(),201);
    }

    @Test
    void removeCocktail_shouldReturnNoContent_WhenItemDeleted() throws Exception {
        //given
        Mockito.doNothing()
                .when(this.service)
                .deleteCocktail(Mockito.any(Long.class));
        //when
        var response = mvc.perform(delete("/api/v1/cocktails/-1").contentType(MediaType.APPLICATION_JSON))
                .andReturn()
                .getResponse();
        //then
        Assert.assertEquals(response.getStatus(),204);
    }
}