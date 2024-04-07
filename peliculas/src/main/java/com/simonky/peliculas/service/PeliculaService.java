package com.simonky.peliculas.service;

import java.util.List;
import java.util.Optional;

import com.simonky.peliculas.model.Pelicula;

public interface PeliculaService {
    
    List<Pelicula> getPeliculas();
    Optional<Pelicula> getPeliculaById(Long id);

    void deletePelicula(Long Id);
    Pelicula createPelicula(Pelicula pelicula);
    Pelicula updatePelicula(Long Id, Pelicula pelicula);

    void Iniciar();
}
