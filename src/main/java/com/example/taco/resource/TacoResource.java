package com.example.taco.resource;

import com.example.taco.domain.Ingredient;
import com.example.taco.domain.Taco;
import lombok.Getter;
import org.springframework.hateoas.RepresentationModel;


import java.util.Date;
import java.util.List;

public class TacoResource extends RepresentationModel<TacoResource> {
    private static final IngredientResourceAssembler
            ingredientAssembler = new IngredientResourceAssembler();
    @Getter
    private final String name;
    @Getter
    private final Date createdAt;
    @Getter
    private final List<IngredientResource> ingredients;

    public TacoResource(Taco taco) {
        this.name = taco.getName();
        this.createdAt = taco.getCreatedAt();
        this.ingredients = ingredientAssembler.toCollectionModel(taco.getIngredients());
    }
}