package com.example.taco.controller;

import com.example.taco.domain.Order;
import com.example.taco.domain.Taco;
import com.example.taco.repository.OrderRepository;
import com.example.taco.repository.TacoRepository;
import com.example.taco.resource.TacoResource;
import com.example.taco.resource.TacoResourceAssembler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.EntityLinks;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.data.domain.PageRequest;

import java.util.List;
import java.util.Optional;



@Slf4j
@RestController
@RequestMapping(path="/design", produces="application/json")
@CrossOrigin(origins="*")
public class DesignTacoController {

    private TacoRepository tacoRepo;
    private OrderRepository repo;

    @Autowired
    EntityLinks entityLinks;

    @Autowired
    public DesignTacoController(TacoRepository tacoRepo, OrderRepository repo) {

        this.tacoRepo = tacoRepo;
        this.repo = repo;
    }

    @GetMapping("/recent")
    public CollectionModel<TacoResource> recentTacos() {
        PageRequest page = PageRequest.of(0, 12, Sort.by("createdAt").descending());
        List<Taco> tacos = tacoRepo.findAll(page).getContent();
        List<TacoResource> tacoResources = new TacoResourceAssembler().toCollectionModel(tacos);
        CollectionModel<TacoResource> recentResources = CollectionModel.of(tacoResources);
        recentResources.add(
                WebMvcLinkBuilder.linkTo(DesignTacoController.class)
                        .slash("recent")
                        .withRel("recents"));
        return recentResources;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Taco> tacoById(@PathVariable("id") Long id) {
        Optional<Taco> optTaco = tacoRepo.findById(id);
        if (optTaco.isPresent()) {
            return new ResponseEntity<Taco>(optTaco.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @PostMapping(consumes="application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public Taco postTaco(@RequestBody Taco taco) {
        return tacoRepo.save(taco);
    }

    @PatchMapping(path="/{orderId}", consumes="application/json")
    public Order patchOrder(@PathVariable("orderId") Long orderId,
                            @RequestBody Order patch) {
        Order order = repo.findById(orderId).get();
        if (patch.getName() != null) {
            order.setName(patch.getName());
        }
        if (patch.getStreet() != null) {
            order.setStreet(patch.getStreet());
        }
        if (patch.getCity() != null) {
            order.setCity(patch.getCity());
        }
        if (patch.getState() != null) {
            order.setState(patch.getState());
        }
        if (patch.getZip() != null) {
            order.setZip(patch.getState());
        }
        if (patch.getCcNumber() != null) {
            order.setCcNumber(patch.getCcNumber());
        }
        if (patch.getCcExpiration() != null) {
            order.setCcExpiration(patch.getCcExpiration());
        }
        if (patch.getCcCVV() != null) {
            order.setCcCVV(patch.getCcCVV());
        }
        return repo.save(order);
    }

    @DeleteMapping("/{orderId}")
    @ResponseStatus(code=HttpStatus.NO_CONTENT)
    public void deleteOrder(@PathVariable("orderId") Long orderId) {
        try {
            repo.deleteById(orderId);
        } catch (EmptyResultDataAccessException e) {}
    }

}