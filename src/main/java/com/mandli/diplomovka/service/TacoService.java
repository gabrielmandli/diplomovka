package com.mandli.diplomovka.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mandli.diplomovka.dto.TacoDto;
import com.mandli.diplomovka.entity.Ingredient;
import com.mandli.diplomovka.entity.Taco;
import com.mandli.diplomovka.repository.IngredientRepository;

@Service
public class TacoService {

    private IngredientRepository ingredientRepository;

    @Autowired
    public TacoService(IngredientRepository ingredientRepository) {
        this.ingredientRepository = ingredientRepository;
    }

    public Taco convertTacoDtoToTaco(TacoDto tacoDto){
        Taco taco = new Taco();
        taco.setName(tacoDto.getName());
        taco.setIngredients(convertIngredientIdListToIngredientList(tacoDto.getIngredients()));
        return taco;
    }

    private List<Ingredient> convertIngredientIdListToIngredientList(List<String> ingredientIdList){
        List<Ingredient> ingredientList = new ArrayList<>();
        for(String ingredientId : ingredientIdList) {
            Optional<Ingredient> ingredient = ingredientRepository.findById(ingredientId);
            if (ingredient.isPresent())
                ingredientList.add(ingredient.get());
        }
        return ingredientList;
    }
}
