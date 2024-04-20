package com.simonky.peliculas.exceptions;

import java.time.Instant;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.ErrorResponseException;

public class PeliculaNotFoundException  extends ErrorResponseException{

    public PeliculaNotFoundException(Long id) {
        super(HttpStatus.NOT_FOUND, asProblemDetail("La película con ID: " + id + " no fue encontrada" ), null);
        
    }

    private static ProblemDetail asProblemDetail(String message) {
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, message);
        problemDetail.setTitle("Pelicula no encontrada");
        problemDetail.setProperty("timestamp", Instant.now());

        return problemDetail;
    }
}