package com.mandli.diplomovka.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.mandli.diplomovka.entity.Ingredient;

@Service
public class IngredientListFilter {

    public static List<Ingredient> filterByType(List<Ingredient> ingredientList, Ingredient. Type type) {
        List filteredList = new ArrayList();
        for (Ingredient ingredient : ingredientList){
            if (ingredient.getType() == type){
                filteredList.add(ingredient);
            }
        }

        return filteredList;
    }
}
