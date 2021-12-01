package com.mandli.diplomovka.service;

import org.springframework.stereotype.Service;

import com.mandli.diplomovka.entity.Ingredient;
import com.mandli.diplomovka.repository.IngredientRepository;

@Service
public class IngredientsToDatabaseSaver {

    private IngredientRepository ingredientRepository;

    public IngredientsToDatabaseSaver(IngredientRepository ingredientRepository){
        this.ingredientRepository = ingredientRepository;
    }

    public void saveIngredientsToDatabase(){
        Ingredient ingredient = new Ingredient("FLTO", "Flour Tortilla", Ingredient.Type.WRAP);
        ingredientRepository.save(ingredient);
        ingredient = new Ingredient("COTO", "Corn Tortilla", Ingredient.Type.WRAP);
        ingredientRepository.save(ingredient);
        ingredient = new Ingredient("GRBF", "Ground Beef", Ingredient.Type.PROTEIN);
        ingredientRepository.save(ingredient);
        ingredient = new Ingredient("CARN", "Carnitas", Ingredient.Type.PROTEIN);
        ingredientRepository.save(ingredient);
        ingredient = new Ingredient("TMTO", "Diced Tomatoes", Ingredient.Type.VEGGIES);
        ingredientRepository.save(ingredient);
        ingredient = new Ingredient("LETC", "Lettuce", Ingredient.Type.VEGGIES);
        ingredientRepository.save(ingredient);
        ingredient = new Ingredient("CHED", "Cheddar", Ingredient.Type.CHEESE);
        ingredientRepository.save(ingredient);
        ingredient = new Ingredient("JACK", "Monterrey Jack", Ingredient.Type.CHEESE);
        ingredientRepository.save(ingredient);
        ingredient = new Ingredient("SLSA", "Salsa", Ingredient.Type.SAUCE);
        ingredientRepository.save(ingredient);
        ingredient = new Ingredient("SRCR", "Sour Cream", Ingredient.Type.SAUCE);
        ingredientRepository.save(ingredient);
    }
}
