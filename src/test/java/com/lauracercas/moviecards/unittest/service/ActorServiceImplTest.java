package com.lauracercas.moviecards.unittest.service;

import com.lauracercas.moviecards.model.Actor;
import com.lauracercas.moviecards.service.actor.ActorServiceImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static com.lauracercas.moviecards.util.Constants.URL_BASE;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

class ActorServiceImplTest {

    @Mock
    private RestTemplate restTemplate;
    private ActorServiceImpl sut;
    private AutoCloseable closeable;
    private static final String URL = URL_BASE + "/actors";

    @BeforeEach
    void setUp() {
        closeable = openMocks(this);
        sut = new ActorServiceImpl(restTemplate);
    }

    @AfterEach
    void tearDown() throws Exception {
        closeable.close();
    }

    @Test
    public void shouldGetAllActors() {

        Actor actor1 = new Actor(1, "Actor 1");
        Actor actor2 = new Actor(2, "Actor 2");
        Actor[] actors = {actor1, actor2};

        when(restTemplate.getForObject(URL, Actor[].class)).thenReturn(actors);

        List<Actor> result = sut.getAllActors();

        assertEquals(2, result.size());
    }

    @Test
    void shouldGetActorById() {
        Actor actor = new Actor();
        actor.setId(1);
        actor.setName("Sample Actor");

        when(restTemplate.getForObject(URL + "/" + 1, Actor.class)).thenReturn(actor);

        Actor result = sut.getActorById(1);

        assertEquals(1, result.getId());
        assertEquals("Sample Actor", result.getName());
    }

    @Test
    public void shouldSaveActor() {
        Actor actor = new Actor();
        actor.setName("New Actor");

        when(restTemplate.postForObject(URL, actor, Actor.class)).thenReturn(actor);

        Actor result = sut.save(actor);

        assertEquals("New Actor", result.getName());
    }

}