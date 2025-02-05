package com.lauracercas.moviecards.service.card;


import com.lauracercas.moviecards.model.Actor;
import com.lauracercas.moviecards.model.Card;
import com.lauracercas.moviecards.model.Movie;
import com.lauracercas.moviecards.service.actor.ActorService;
import com.lauracercas.moviecards.service.movie.MovieService;
import com.lauracercas.moviecards.util.Messages;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CardServiceImpl implements CardService {

    @Autowired
    ActorService actorService;

    @Autowired
    MovieService movieService;

    public CardServiceImpl(ActorService actorService, MovieService movieService) {
        this.actorService = actorService;
        this.movieService = movieService;
    }

    @Override
    public String registerActorInMovie(Card card) {
        Integer actorId = card.getIdActor();
        Integer movieId = card.getIdMovie();

        Actor actor = actorService.getActorById(actorId);
        Movie movie = movieService.getMovieById(movieId);

        if (actor == null || movie == null) {
            return Messages.ERROR_MESSAGE;
        }

        if (movie.existActorInMovie(actor)) {
            return Messages.CARD_ALREADY_EXISTS;
        }

        movie.addActor(actor);
        movieService.save(movie);
        return Messages.CARD_REGISTRATION_SUCCESS;
    }


}
