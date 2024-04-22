package com.simonky.peliculas.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.simonky.peliculas.model.Pelicula;
import com.simonky.peliculas.service.PeliculaService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;



@RestController
@RequestMapping("peliculas")
@Validated
public class PeliculaController {
    

    @Autowired
    private PeliculaService peliculaService;


    @GetMapping
    public ResponseEntity<CollectionModel<EntityModel<Pelicula>>>  getPeliculas() {
        List<Pelicula> peliculas = this.peliculaService.getPeliculas();

        List<EntityModel<Pelicula>> peliculasRes = peliculas.stream()
            .map(p -> EntityModel.of(p,
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getPeliculaById(p.getId())).withSelfRel()
            )).collect(Collectors.toList());
        
        WebMvcLinkBuilder linkTo = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getPeliculas());
        CollectionModel<EntityModel<Pelicula>> recursos = CollectionModel.of(peliculasRes, linkTo.withRel("peliculas"));

        return ResponseEntity.ok(recursos);
    }


    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<Pelicula>> getPeliculaById(@PathVariable("id") Long id) {
        
        EntityModel<Pelicula> res = EntityModel.of(this.peliculaService.getPeliculaById(id),
            WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getPeliculaById(id)).withSelfRel(),
            WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getPeliculas()).withRel("peliculas")
        );
        return ResponseEntity.ok(res);
    }


    @PostMapping
    public ResponseEntity<EntityModel<Pelicula>> createPelicula(@Valid @RequestBody Pelicula pelicula) {

        Pelicula peliculaCreada = this.peliculaService.createPelicula(pelicula);
        EntityModel<Pelicula> res = EntityModel.of(peliculaCreada,
            WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getPeliculaById(peliculaCreada.getId())).withSelfRel(),
            WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getPeliculas()).withRel("peliculas")
        );

        return ResponseEntity.ok(res);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePelicula(@PathVariable("id") Long id) {
        this.peliculaService.deletePelicula(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<EntityModel<Pelicula>> updatePelicula(@PathVariable("id") Long id, @Valid @RequestBody Pelicula pelicula) {

        Pelicula peliculaActualizada = this.peliculaService.updatePelicula(id, pelicula);
        EntityModel<Pelicula> res = EntityModel.of(peliculaActualizada,
            WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getPeliculaById(peliculaActualizada.getId())).withSelfRel(),
            WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getPeliculas()).withRel("peliculas")
        );

        return ResponseEntity.ok(res);

    }
    
}
