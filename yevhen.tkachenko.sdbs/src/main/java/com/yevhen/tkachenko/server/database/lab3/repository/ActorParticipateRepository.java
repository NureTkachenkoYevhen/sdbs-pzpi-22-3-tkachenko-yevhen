package main.java.com.yevhen.tkachenko.server.database.lab3.repository;

import main.java.com.yevhen.tkachenko.server.database.lab3.model.Actor;
import main.java.com.yevhen.tkachenko.server.database.lab3.model.ActorParticipate;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ActorParticipateRepository {
    private final Connection connection;

    public ActorParticipateRepository(Connection connection) {
        this.connection = connection;
    }

    public List<ActorParticipate> getActorTotalFees()throws SQLException {
        String query = "SELECT * FROM get_actor_total_fees();";
        List<ActorParticipate> actors = new ArrayList<>();
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                ActorParticipate actorParticipate = new ActorParticipate();

                Actor actor = new Actor();
                actor.setActorFullName(rs.getString("actor_full_name"));

                actorParticipate.setActor(actor);
                actorParticipate.setFeeNum(rs.getBigDecimal("total_fee"));

                actors.add(actorParticipate);
            }
        }
        return actors;
    }
}
