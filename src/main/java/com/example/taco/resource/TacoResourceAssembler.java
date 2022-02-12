package com.example.taco.resource;

import com.example.taco.controller.DesignTacoController;
import com.example.taco.domain.Taco;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TacoResourceAssembler
        extends RepresentationModelAssemblerSupport<Taco, TacoResource> {

    public TacoResourceAssembler() {
        super(DesignTacoController.class, TacoResource.class);
    }

    @Override
    protected TacoResource instantiateModel(Taco taco) {
        return new TacoResource(taco);
    }

    @Override
    public TacoResource toModel(Taco taco) {
        return createModelWithId(taco.getId(), taco);
    }

    public List<TacoResource> toCollectionModel(List<Taco> tacos){
        List<TacoResource> tacoResources = tacos.stream().map( x -> toModel(x)).collect(Collectors.toList());
        return tacoResources;
    }
}