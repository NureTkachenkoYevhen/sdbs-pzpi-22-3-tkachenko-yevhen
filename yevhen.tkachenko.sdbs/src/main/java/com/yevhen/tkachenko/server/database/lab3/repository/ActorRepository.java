package main.java.com.yevhen.tkachenko.server.database.lab3.repository;

import main.java.com.yevhen.tkachenko.server.database.lab3.model.Actor;
import main.java.com.yevhen.tkachenko.server.database.lab3.model.ActorParticipate;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ActorRepository {
    private final Connection connection;

    public ActorRepository(Connection connection) {
        this.connection = connection;
    }

    public List<Actor> getAllActors() throws SQLException {
        String query = "SELECT * FROM actor";
        List<Actor> actors = new ArrayList<>();
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                actors.add(new Actor(
                        rs.getInt("actor_id"),
                        rs.getString("actor_full_name"),
                        rs.getDate("actor_date"),
                        rs.getString("nationality"),
                        rs.getString("gender")
                ));
            }
        }
        return actors;
    }

    public List<Actor> getActorsByFilmId(int filmId) throws SQLException {
        String query =
                "SELECT actor.actor_id, actor.actor_full_name, actor.actor_date, actor.nationality, actor.gender " +
                        "FROM actor, actor_participate WHERE actor_participate.film_id = " + filmId + " AND " +
                        "actor_participate.actor_id = actor.actor_id";
        List<Actor> actors = new ArrayList<>();
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                actors.add(new Actor(
                        rs.getInt("actor_id"),
                        rs.getString("actor_full_name"),
                        rs.getDate("actor_date"),
                        rs.getString("nationality"),
                        rs.getString("gender")
                ));
            }
        }
        return actors;
    }
}

