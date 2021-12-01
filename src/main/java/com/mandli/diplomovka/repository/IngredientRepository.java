package com.mandli.diplomovka.repository;

import org.springframework.data.repository.CrudRepository;

import com.mandli.diplomovka.entity.Ingredient;

public interface IngredientRepository extends CrudRepository<Ingredient, String> {

}
