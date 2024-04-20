package com.simonky.peliculas.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.simonky.peliculas.model.Pelicula;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class PeliculaRepositoryTest {

    @Autowired
    private PeliculaRepository peliculaRepository;

    @Test
    public void guardarPelicula(){
        Pelicula peli = new Pelicula();
        peli.setAnno(2000);
        peli.setDirector("Cuentin Taratin");
        peli.setGenero("Drama");
        peli.setSinopsis("Pelicula de drama");
        peli.setTitulo("Perros de la vereda");

        Pelicula res = peliculaRepository.save(peli);

        assertNotNull(res.getId());
        assertEquals(2000, res.getAnno());
        assertEquals("Cuentin Taratin", res.getDirector());
        assertEquals("Drama", res.getGenero());
        assertEquals("Pelicula de drama", res.getSinopsis());
        assertEquals("Perros de la vereda", res.getTitulo());
    }

    @Test
    public void eliminarPelicula() {
        Pelicula peli = new Pelicula();
        peli.setAnno(2000);
        peli.setDirector("Cuentin Taratin");
        peli.setGenero("Drama");
        peli.setSinopsis("Pelicula de drama");
        peli.setTitulo("Perros de la vereda");

        peliculaRepository.save(peli);

        Pelicula res = peliculaRepository.save(peli);

        assertNotNull(res.getId());

        
        peliculaRepository.deleteById(res.getId());

        Optional<Pelicula> peliculaEliminada = peliculaRepository.findById(res.getId());
        assertFalse(peliculaEliminada.isPresent());
    }
    
}
