package com.lauracercas.moviecards.controller;

import com.lauracercas.moviecards.model.Actor;
import com.lauracercas.moviecards.model.Movie;
import com.lauracercas.moviecards.service.actor.ActorService;
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
public class ActorController {

    private static final String ACTOR_ATTRIBUTE = "actor";
    private static final String TITLE_ATTRIBUTE = "title";
    private static final String ACTORS_FORM_VIEW = "actors/form";
    private static final String ACTORS_LIST_VIEW = "actors/list";

    private final ActorService actorService;

    public ActorController(ActorService actorService) {
        this.actorService = actorService;
    }

    @GetMapping("actors")
    public String getActorsList(Model model) {
        model.addAttribute("actors", actorService.getAllActors());
        return ACTORS_LIST_VIEW;
    }

    @GetMapping("actors/new")
    public String newActor(Model model) {
        model.addAttribute(ACTOR_ATTRIBUTE, new Actor());
        model.addAttribute(TITLE_ATTRIBUTE, Messages.NEW_ACTOR_TITLE);
        return ACTORS_FORM_VIEW;
    }

    @PostMapping("saveActor")
    public String saveActor(@ModelAttribute Actor actor, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return ACTORS_FORM_VIEW;
        }
        Actor actorSaved = actorService.save(actor);
        if (actor.getId() != null) {
            model.addAttribute("message", Messages.UPDATED_ACTOR_SUCCESS);
        } else {
            model.addAttribute("message", Messages.SAVED_ACTOR_SUCCESS);
        }

        model.addAttribute(ACTOR_ATTRIBUTE, actorSaved);
        model.addAttribute(TITLE_ATTRIBUTE, Messages.EDIT_ACTOR_TITLE);
        return ACTORS_FORM_VIEW;
    }

    @GetMapping("editActor/{actorId}")
    public String editActor(@PathVariable Integer actorId, Model model) {
        Actor actor = actorService.getActorById(actorId);
        List<Movie> movies = actor.getMovies();
        model.addAttribute(ACTOR_ATTRIBUTE, actor);
        model.addAttribute("movies", movies);

        model.addAttribute(TITLE_ATTRIBUTE, Messages.EDIT_ACTOR_TITLE);

        return ACTORS_FORM_VIEW;
    }

}
