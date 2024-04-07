package com.simonky.peliculas.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.simonky.peliculas.model.Pelicula;

public interface PeliculaRepository  extends JpaRepository<Pelicula, Long>{
    
}
