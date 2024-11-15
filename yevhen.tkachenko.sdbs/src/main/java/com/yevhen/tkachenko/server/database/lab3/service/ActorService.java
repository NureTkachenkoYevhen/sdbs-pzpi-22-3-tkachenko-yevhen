package main.java.com.yevhen.tkachenko.server.database.lab3.service;

import main.java.com.yevhen.tkachenko.server.database.lab3.model.Actor;
import main.java.com.yevhen.tkachenko.server.database.lab3.repository.ActorRepository;

import java.sql.SQLException;
import java.util.List;

public class ActorService {
    private final ActorRepository actorRepository;

    public ActorService(ActorRepository actorRepository) {
        this.actorRepository = actorRepository;
    }

    public List<Actor> getAllActors() throws SQLException {
        return actorRepository.getAllActors();
    }
    public List<Actor> getActorsByFilmId(int filmId) throws SQLException {
        return actorRepository.getActorsByFilmId(filmId);
    }


}