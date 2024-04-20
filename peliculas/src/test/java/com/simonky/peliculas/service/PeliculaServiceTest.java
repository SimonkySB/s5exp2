package com.simonky.peliculas.service;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.simonky.peliculas.exceptions.PeliculaNotFoundException;
import com.simonky.peliculas.model.Pelicula;
import com.simonky.peliculas.repository.PeliculaRepository;

@ExtendWith(MockitoExtension.class)
public class PeliculaServiceTest {
    @InjectMocks
    private PeliculaServiceImpl peliculaService;

    @Mock
    private PeliculaRepository peliculaRepositoryTest;

    @Test
    public void guardarPeliculaTest(){
        Pelicula peli = new Pelicula();
        peli.setAnno(2000);
        peli.setDirector("Cuentin Taratin");
        peli.setGenero("Drama");
        peli.setSinopsis("Pelicula de drama");
        peli.setTitulo("Perros de la vereda");

        when(peliculaRepositoryTest.save(any())).thenReturn(peli);

        Pelicula res = peliculaService.createPelicula(peli);

        assertEquals(2000, res.getAnno());
        assertEquals("Cuentin Taratin", res.getDirector());
        assertEquals("Drama", res.getGenero());
        assertEquals("Pelicula de drama", res.getSinopsis());
        assertEquals("Perros de la vereda", res.getTitulo());
    }


    @Test
    public void eliminarPeliculaExistenteTest() {
        Long idPelicula = 1L;
        Pelicula peli = new Pelicula();
        peli.setAnno(2000);
        peli.setDirector("Cuentin Taratin");
        peli.setGenero("Drama");
        peli.setSinopsis("Pelicula de drama");
        peli.setTitulo("Perros de la vereda");

        when(peliculaRepositoryTest.findById(idPelicula)).thenReturn(Optional.of(peli));

        peliculaService.deletePelicula(idPelicula);

        verify(peliculaRepositoryTest, times(1)).deleteById(idPelicula);
    }

    @Test
    public void eliminarPeliculaNoExistenteTest() {
        
        Long idPeliculaNoExistente = 999L;

        when(peliculaRepositoryTest.findById(idPeliculaNoExistente)).thenReturn(Optional.empty());

        assertThrows(PeliculaNotFoundException.class, () -> peliculaService.deletePelicula(idPeliculaNoExistente));

        verify(peliculaRepositoryTest, never()).deleteById(idPeliculaNoExistente);
    }
}
