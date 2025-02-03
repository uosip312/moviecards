package com.lauracercas.moviecards.integrationtest.repositories;

import com.lauracercas.moviecards.model.Actor;
import com.lauracercas.moviecards.repositories.ActorJPA;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Date;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class ActorJPAIT {

    @Autowired
    private ActorJPA actorJPA;

    @Test
    public void testSaveActor() {
        Actor actor = new Actor();
        actor.setName("actor");
        actor.setBirthDate(new Date());
        actor.setCountry("spain");
        actor.setDeadDate(new Date());

        Actor savedActor = actorJPA.save(actor);

        assertNotNull(savedActor.getId());

        Optional<Actor> foundActor = actorJPA.findById(savedActor.getId());

        assertTrue(foundActor.isPresent());
        assertEquals(savedActor, foundActor.get());
        assertNotNull(foundActor.get().getDeadDate());
    }

    @Test
    public void testFindById() {
        Actor actor = new Actor();
        actor.setName("actor");
        actor.setBirthDate(new Date());
        actor.setDeadDate(new Date());
        Actor savedActor = actorJPA.save(actor);

        Optional<Actor> foundActor = actorJPA.findById(savedActor.getId());

        assertTrue(foundActor.isPresent());
        assertEquals(savedActor, foundActor.get());
        assertNotNull(foundActor.get().getDeadDate());
    }
}
