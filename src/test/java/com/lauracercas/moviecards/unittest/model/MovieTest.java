package com.lauracercas.moviecards.unittest.model;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.lauracercas.moviecards.model.Actor;
import com.lauracercas.moviecards.model.Movie;

import static org.junit.jupiter.api.Assertions.*;

public class MovieTest {

    Movie movie = new Movie();

    @Test
    void testSetGetId() {
        Integer idExample = 1;
        movie.setId(idExample);
        assertEquals(idExample, movie.getId());
    }

    @Test
    void testSetGetTitle() {
        String titleExample = "Sample title";
        movie.setTitle(titleExample);
        assertEquals(titleExample, movie.getTitle());
    }

    @Test
    void testSetGetReleaseYear() {
        Integer releaseYearExample = 2000;
        movie.setReleaseYear(releaseYearExample);
        assertEquals(releaseYearExample,movie.getReleaseYear());
    }

    @Test
    void testSetGetDuration() {
        Integer durationExample = 100;
        movie.setDuration(durationExample);
        assertEquals(durationExample,movie.getDuration());
    }    

    @Test
    void testSetGetCountry() {
        String countryExample = "Sample country";
        movie.setCountry(countryExample);
        assertEquals(countryExample,movie.getCountry());
    }

    @Test
    void testSetGetDirector() {
        String directorExample = "Sample director";
        movie.setDirector(directorExample);
        assertEquals(directorExample,movie.getDirector());
    }

    @Test
    void testSetGetGenre() {
        String genreExample = "Sample genre";
        movie.setGenre(genreExample);
        assertEquals(genreExample,movie.getGenre());
    }

    @Test
    void testSetGetSinopsis() {
        String sinopsisExample = "Sample sinopsis";
        movie.setSinopsis(sinopsisExample);
        assertEquals(sinopsisExample,movie.getSinopsis());
    }

    @Test
    void testSetGetActors() {
        List<Actor> actorsExample = new ArrayList<Actor>();
        movie.setActors(actorsExample);
        assertEquals(actorsExample, movie.getActors());
    }

    @Test
    void testAddActor() {
        List<Actor> actorsExample = new ArrayList<Actor>();
        movie.setActors(actorsExample);
        Actor actorExample = new Actor(1,"Sample name");
        movie.addActor(actorExample);
        assertTrue(movie.getActors().contains(actorExample), "El actor debería estar en la lista de actores");
        assertEquals(1, movie.getActors().size(), "El tamaño de la lista de actores debería ser 1");
    }

    @Test
    void testExistActorInMovie() {
        List<Actor> actorsExample = new ArrayList<Actor>();
        Actor actorExample = new Actor(2,"Sample name");
        actorsExample.add(actorExample);
        movie.setActors(actorsExample);
        assertTrue(movie.existActorInMovie(actorExample), "El actor debería existir en la película");
        assertFalse(movie.getActors().isEmpty(), "La lista de actores no debería estar vacía");
    }

}
