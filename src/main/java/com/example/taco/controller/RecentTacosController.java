package com.example.taco.controller;

import com.example.taco.domain.Taco;
import com.example.taco.repository.TacoRepository;
import com.example.taco.resource.TacoResource;
import com.example.taco.resource.TacoResourceAssembler;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RepositoryRestController
public class RecentTacosController {
    private TacoRepository tacoRepo;
    public RecentTacosController(TacoRepository tacoRepo) {
        this.tacoRepo = tacoRepo;
    }
    @GetMapping(path="/tacos/recent", produces="application/hal+json")
    @ResponseStatus(HttpStatus.OK)
    public CollectionModel<TacoResource> recentTacos() {
        PageRequest page = PageRequest.of(0, 12, Sort.by("createdAt").descending());
        List<Taco> tacos = tacoRepo.findAll(page).getContent();
        List<TacoResource> tacoResources = new TacoResourceAssembler().toCollectionModel(tacos);
        CollectionModel<TacoResource> recentResources =
                CollectionModel.of(tacoResources);
        recentResources.add(
                WebMvcLinkBuilder.linkTo(methodOn(RecentTacosController.class).recentTacos())
                        .withRel("recents"));

        return recentResources;
    }
}
