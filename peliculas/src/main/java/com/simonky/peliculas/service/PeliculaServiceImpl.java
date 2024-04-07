package com.simonky.peliculas.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public Optional<Pelicula> getPeliculaById(Long id) {
        return this.peliculaRepository.findById(id);
    }

    @Override
    public void deletePelicula(Long Id) {
        this.peliculaRepository.deleteById(Id);
    }

    @Override
    public Pelicula createPelicula(Pelicula pelicula) {
        return this.peliculaRepository.save(pelicula);
    }

    @Override
    public Pelicula updatePelicula(Long Id, Pelicula pelicula) {
        if(this.peliculaRepository.existsById(Id)){
            pelicula.setId(Id);
            return this.peliculaRepository.save(pelicula);
        } 
        else {
            return null;
        }
        
    }

    @Override
    public void Iniciar() {
        List<Pelicula> peliculas = new ArrayList<>();
        
        for(int i = 0; i <= 5; i++) {
            Pelicula peli = new Pelicula();
            peli.setAnno(2000 + i);
            peli.setDirector("Juan Carlos");
            peli.setGenero("Drama");
            peli.setSinopsis("Pelicula de drampa");
            peli.setTitulo("Pelicula parte " + (i + 1));;
            peliculas.add(peli);
        }
        
        this.peliculaRepository.saveAll(peliculas);
    }
    
}
