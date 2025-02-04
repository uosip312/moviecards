package com.lauracercas.moviecards.controller;

import com.lauracercas.moviecards.model.Actor;
import com.lauracercas.moviecards.model.Movie;
import com.lauracercas.moviecards.service.movie.MovieService;
import com.lauracercas.moviecards.util.Messages;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class MovieController {

    private static final String ATTRIBUTE_MOVIE = "movie";
    private static final String ATTRIBUTE_TITLE = "title";
    private static final String VIEW_MOVIES_FORM = "movies/form";
    private static final String VIEW_MOVIES_LIST = "movies/list";

    private final MovieService movieService;

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping("movies")
    public String getMoviesList(Model model) {
        model.addAttribute("movies", movieService.getAllMovies());
        return VIEW_MOVIES_LIST;
    }

    @GetMapping("movies/new")
    public String newMovie(Model model) {
        model.addAttribute(ATTRIBUTE_MOVIE, new Movie());
        model.addAttribute(ATTRIBUTE_TITLE, Messages.NEW_MOVIE_TITLE);
        return VIEW_MOVIES_FORM;
    }

    @PostMapping("saveMovie")
    public String saveMovie(@ModelAttribute Movie movie, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return VIEW_MOVIES_FORM;
        }
        Movie movieSaved = movieService.save(movie);
        if (movieSaved.getId() != null) {
            model.addAttribute("message", Messages.UPDATED_MOVIE_SUCCESS);
        } else {
            model.addAttribute("message", Messages.SAVED_MOVIE_SUCCESS);
        }

        model.addAttribute(ATTRIBUTE_MOVIE, movieSaved);
        model.addAttribute(ATTRIBUTE_TITLE, Messages.EDIT_MOVIE_TITLE);
        return VIEW_MOVIES_FORM;
    }

    @GetMapping("editMovie/{movieId}")
    public String editMovie(@PathVariable Integer movieId, Model model) {
        Movie movie = movieService.getMovieById(movieId);
        List<Actor> actors = movie.getActors();
        model.addAttribute(ATTRIBUTE_MOVIE, movie);
        model.addAttribute("actors", actors);
        model.addAttribute(ATTRIBUTE_TITLE, Messages.EDIT_MOVIE_TITLE);

        return VIEW_MOVIES_FORM;
    }


}
