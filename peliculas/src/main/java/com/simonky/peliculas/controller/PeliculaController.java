package com.simonky.peliculas.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.simonky.peliculas.model.Pelicula;
import com.simonky.peliculas.service.PeliculaService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;



@RestController
@RequestMapping("peliculas")
public class PeliculaController {
    

    private PeliculaService peliculaService;


    public PeliculaController(PeliculaService peliculaService) {
        this.peliculaService = peliculaService;
        this.peliculaService.Iniciar();
    }


    @GetMapping
    public List<Pelicula> getPeliculas() {
        return this.peliculaService.getPeliculas();
    }


    @GetMapping("/{id}")
    public Optional<Pelicula> getPeliculaById(@PathVariable("id") Long id) {
        return this.peliculaService.getPeliculaById(id);
    }


    @PostMapping
    public Pelicula createPelicula(@RequestBody Pelicula pelicula) {
        return this.peliculaService.createPelicula(pelicula);
    }


    @DeleteMapping("/{id}")
    public void deletePelicula(@PathVariable("id") Long id) {
        this.peliculaService.deletePelicula(id);
    }

    @PutMapping("/{id}")
    public Pelicula putMethodName(@PathVariable("id") Long id, @RequestBody Pelicula pelicula) {
        return this.peliculaService.updatePelicula(id, pelicula);

    }
    
}
