package com.lauracercas.moviecards.service.actor;


import com.lauracercas.moviecards.model.Actor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static com.lauracercas.moviecards.util.Constants.URL_BASE;

@Service
public class ActorServiceImpl implements ActorService {

    private static final String URL = URL_BASE + "/actors";

    private final RestTemplate restTemplate;

    public ActorServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public List<Actor> getAllActors() {
        Actor[] actors = restTemplate.getForObject(URL, Actor[].class);
        assert actors != null;
        return List.of(actors);
    }

    @Override
    public Actor save(Actor actor) {
        if (actor.getId() == null) {
            restTemplate.postForObject(URL, actor, Actor.class);
        } else {
            restTemplate.put(URL, actor);
        }
        return actor;
    }

    @Override
    public Actor getActorById(Integer actorId) {
        return restTemplate.getForObject(URL + "/" + actorId, Actor.class);
    }
}
