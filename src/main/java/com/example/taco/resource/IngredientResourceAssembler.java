package com.example.taco.resource;

import com.example.taco.domain.Ingredient;
import com.example.taco.domain.Taco;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;

import java.util.List;
import java.util.stream.Collectors;

class IngredientResourceAssembler extends
        RepresentationModelAssemblerSupport<Ingredient, IngredientResource> {

    public IngredientResourceAssembler() {
        super(IngredientController2.class, IngredientResource.class);
    }

    @Override
    public IngredientResource toModel(Ingredient ingredient) {
        return createModelWithId(ingredient.getId(), ingredient);
    }

    @Override
    protected IngredientResource instantiateModel(Ingredient ingredient) {
        return new IngredientResource(ingredient);
    }

    public List<IngredientResource> toCollectionModel(List<Ingredient> ingredients) {
        List<IngredientResource> ingredientsResources = ingredients.stream().map(x -> toModel(x)).collect(Collectors.toList());
        return ingredientsResources;
    }
}