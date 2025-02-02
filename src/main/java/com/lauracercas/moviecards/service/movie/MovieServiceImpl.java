package com.lauracercas.moviecards.service.movie;


import com.lauracercas.moviecards.model.Movie;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static com.lauracercas.moviecards.util.Constants.URL_BASE;

@Service
public class MovieServiceImpl implements MovieService {

    private static final String url = URL_BASE + "/movies";

    private final RestTemplate restTemplate;

    public MovieServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public List<Movie> getAllMovies() {
        Movie[] movies = restTemplate.getForObject(url, Movie[].class);
        assert movies != null;
        return List.of(movies);
    }

    @Override
    public Movie save(Movie movie) {
        if (movie.getId() == null) {
            restTemplate.postForObject(url, movie, Movie.class);
        } else {
            restTemplate.put(url, movie);
        }
        return movie;
    }

    @Override
    public Movie getMovieById(Integer movieId) {
        return restTemplate.getForObject(url + "/" + movieId, Movie.class);
    }
}
