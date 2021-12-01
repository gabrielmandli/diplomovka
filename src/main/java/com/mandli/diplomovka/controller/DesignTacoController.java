package com.mandli.diplomovka.controller;

import java.util.ArrayList;
import java.util.List;

import jakarta.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.mandli.diplomovka.dto.TacoDto;
import com.mandli.diplomovka.entity.Ingredient;
import com.mandli.diplomovka.entity.Ingredient.Type;
import com.mandli.diplomovka.entity.Order;
import com.mandli.diplomovka.entity.Taco;
import com.mandli.diplomovka.repository.IngredientRepository;
import com.mandli.diplomovka.repository.TacoRepository;
import com.mandli.diplomovka.service.IngredientListFilter;
import com.mandli.diplomovka.service.IngredientsToDatabaseSaver;
import com.mandli.diplomovka.service.TacoService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/design")
@SessionAttributes("order")
public class DesignTacoController {

    IngredientListFilter ingredientListFilter;
    IngredientRepository ingredientRepository;
    TacoRepository tacoRepository;
    TacoService tacoService;
    IngredientsToDatabaseSaver ingredientsToDatabaseSaver;

    public DesignTacoController(IngredientListFilter ingredientListFilter, IngredientRepository ingredientRepository, TacoRepository tacoRepository, TacoService tacoService, IngredientsToDatabaseSaver ingredientsToDatabaseSaver) {
        this.ingredientListFilter = ingredientListFilter;
        this.ingredientRepository = ingredientRepository;
        this.tacoRepository = tacoRepository;
        this.tacoService = tacoService;
        this.ingredientsToDatabaseSaver = ingredientsToDatabaseSaver;
    }

    @ModelAttribute
    public void addIngredientsToModel(Model model){
        List<Ingredient> ingredientList = new ArrayList<>();
        ingredientRepository.findAll().forEach(ingredientList::add);
        if(ingredientList.isEmpty()){
            ingredientsToDatabaseSaver.saveIngredientsToDatabase();
            ingredientRepository.findAll().forEach(ingredientList::add);
        }

        Type[] types = Type.values();
        for (Type type : types){
            model.addAttribute(type.toString().toLowerCase(), ingredientListFilter.filterByType(ingredientList, type));
        }
    }

   @ModelAttribute(name = "order")
    public Order order() {
        return new Order();
    }

    @GetMapping
    public String showDesignForm(@ModelAttribute("taco") TacoDto tacoDto) {
        return "design";
    }

    @PostMapping
    public String processDesign(@Valid @ModelAttribute("taco") TacoDto tacoDto, BindingResult bindingResult, Order order) {
        if(bindingResult.hasErrors()){
            return "design";
        }

        Taco taco = tacoService.convertTacoDtoToTaco(tacoDto);
        tacoRepository.save(taco);
        order.addTaco(taco);

        return "redirect:/orders/current";
    }

}
