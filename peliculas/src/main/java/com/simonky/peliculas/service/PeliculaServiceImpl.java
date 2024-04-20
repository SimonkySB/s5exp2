package com.simonky.peliculas.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.simonky.peliculas.exceptions.PeliculaNotFoundException;
import com.simonky.peliculas.model.Pelicula;
import com.simonky.peliculas.repository.PeliculaRepository;


@Service
public class PeliculaServiceImpl implements PeliculaService {

    @Autowired
    private PeliculaRepository peliculaRepository;
    
    @Override
    public List<Pelicula> getPeliculas() {
        return this.peliculaRepository.findAll();
    }

    @Override
    public Pelicula getPeliculaById(Long id) {
        return this.peliculaRepository.findById(id)
            .orElseThrow(() -> new PeliculaNotFoundException(id));
    }

    @Override
    public void deletePelicula(Long id) {
        this.peliculaRepository.findById(id)
            .orElseThrow(() -> new PeliculaNotFoundException(id));

        this.peliculaRepository.deleteById(id);
    }

    @Override
    public Pelicula createPelicula(Pelicula pelicula) {
        return this.peliculaRepository.save(pelicula);
    }

    @Override
    public Pelicula updatePelicula(Long id, Pelicula pelicula) {
        pelicula.setId(id);

        this.peliculaRepository.findById(pelicula.getId())
            .orElseThrow(() -> new PeliculaNotFoundException(pelicula.getId()));

        return this.peliculaRepository.save(pelicula);
    }

}
