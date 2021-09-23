package com.abnamro.nl.recipe.controllers;

import com.abnamro.nl.recipe.dto.RecipeCreateDTO;
import com.abnamro.nl.recipe.dto.RecipeGetDTO;
import com.abnamro.nl.recipe.dto.RecipeListDTO;
import com.abnamro.nl.recipe.dto.RecipeUpdateDTO;
import com.abnamro.nl.recipe.entities.Recipe;
import com.abnamro.nl.recipe.logic.RecipeService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "/recipe")
@AllArgsConstructor
public class RecipeController {


    private ModelMapper modelMapper;
    private RecipeService recipeService;


    @GetMapping
    public List<RecipeListDTO> getRecipes(
            @RequestParam(name = "size", defaultValue = "10") int size,
            @RequestParam(name = "page", defaultValue = "0") int page) {
        return recipeService.getRecipes(page, size).
                stream().
                map(result -> modelMapper.map(result, RecipeListDTO.class)).
                collect(Collectors.toList());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public RecipeGetDTO createRecipe(@Valid @RequestBody RecipeCreateDTO recipeToCreate, Principal principal) {
        Recipe recipe = modelMapper.map(recipeToCreate, Recipe.class);
        recipe.setCreatedOn(LocalDateTime.now().withNano(0));
        return modelMapper.map(recipeService.createRecipe(recipe, principal.getName()), RecipeGetDTO.class);

    }

    @GetMapping("{recipeId}")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public RecipeGetDTO getRecipe(@PathVariable("recipeId") long recipeId) {
        return modelMapper.map(recipeService.getRecipe(recipeId), RecipeGetDTO.class);
    }

    @PutMapping("{recipeId}")
    @ResponseBody
    public RecipeGetDTO updateRecipe(@PathVariable long recipeId, @RequestBody @Valid RecipeUpdateDTO recipeUpdateDTO, Principal principal) {
        Recipe recipeToUpdate = modelMapper.map(recipeUpdateDTO, Recipe.class);
        recipeToUpdate.setRecipeId(recipeId);
        return modelMapper.map(recipeService.updateRecipe(recipeToUpdate, principal.getName()), RecipeGetDTO.class);
    }

    @DeleteMapping("{recipeId}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteRecipe(@PathVariable("recipeId") long recipeId, Principal principal) {
        recipeService.deleteRecipe(recipeId, principal.getName());
    }

}
