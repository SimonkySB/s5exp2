package com.simonky.peliculas.controller;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.simonky.peliculas.model.Pelicula;
import com.simonky.peliculas.service.PeliculaServiceImpl;


@WebMvcTest(PeliculaController.class)
public class PeliculaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PeliculaServiceImpl peliculaServiceMock;


    @Test
    public void listarPeliculasTest() throws Exception {
        Pelicula peli = new Pelicula();
        peli.setAnno(2000);
        peli.setDirector("Cuentin Taratin");
        peli.setGenero("Drama");
        peli.setSinopsis("Pelicula de drama");
        peli.setTitulo("Perros de la vereda");

        Pelicula peli2 = new Pelicula();
        peli2.setAnno(2000);
        peli2.setDirector("Cuentin Taratin");
        peli2.setGenero("Drama");
        peli2.setSinopsis("Pelicula de drama");
        peli2.setTitulo("Perros de la vereda");

        List<Pelicula> pelis = Arrays.asList(peli, peli2);
        when(peliculaServiceMock.getPeliculas()).thenReturn(pelis);

        mockMvc.perform(MockMvcRequestBuilders.get("/peliculas"))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.jsonPath("$._embedded.peliculaList", Matchers.hasSize(2)))
            .andExpect(MockMvcResultMatchers.jsonPath("$._embedded.peliculaList[0].titulo").value("Perros de la vereda"))
            .andExpect(MockMvcResultMatchers.jsonPath("$._embedded.peliculaList[0].anno").value(2000))
            .andExpect(MockMvcResultMatchers.jsonPath("$._embedded.peliculaList[0].director").value("Cuentin Taratin"))
            .andExpect(MockMvcResultMatchers.jsonPath("$._embedded.peliculaList[0].genero").value("Drama"))
            .andExpect(MockMvcResultMatchers.jsonPath("$._embedded.peliculaList[0].sinopsis").value("Pelicula de drama"))
            .andExpect(MockMvcResultMatchers.jsonPath("$._embedded.peliculaList[1].titulo").value("Perros de la vereda"))
            .andExpect(MockMvcResultMatchers.jsonPath("$._embedded.peliculaList[1].anno").value(2000))
            .andExpect(MockMvcResultMatchers.jsonPath("$._embedded.peliculaList[1].director").value("Cuentin Taratin"))
            .andExpect(MockMvcResultMatchers.jsonPath("$._embedded.peliculaList[1].genero").value("Drama"))
            .andExpect(MockMvcResultMatchers.jsonPath("$._embedded.peliculaList[1].sinopsis").value("Pelicula de drama"))
            ;
    }

    @Test
    public void eliminarPeliculaTest() throws Exception {
        Long idPelicula = 1L;

        doNothing().when(peliculaServiceMock).deletePelicula(idPelicula);

        mockMvc.perform(MockMvcRequestBuilders.delete("/peliculas/{id}", idPelicula))
        .andExpect(MockMvcResultMatchers.status().isNoContent());

        verify(peliculaServiceMock, times(1)).deletePelicula(idPelicula);

    }


}
