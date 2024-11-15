package main.java.com.yevhen.tkachenko.server.database.lab3.service;

import main.java.com.yevhen.tkachenko.server.database.lab3.model.ActorParticipate;
import main.java.com.yevhen.tkachenko.server.database.lab3.repository.ActorParticipateRepository;

import java.sql.SQLException;
import java.util.List;

public class ActorParticipateService {
    private final ActorParticipateRepository actorRepository;

    public ActorParticipateService(ActorParticipateRepository actorRepository) {
        this.actorRepository = actorRepository;
    }
    public List<ActorParticipate> getActorTotalFees() throws SQLException {
        return actorRepository.getActorTotalFees();
    }
}
