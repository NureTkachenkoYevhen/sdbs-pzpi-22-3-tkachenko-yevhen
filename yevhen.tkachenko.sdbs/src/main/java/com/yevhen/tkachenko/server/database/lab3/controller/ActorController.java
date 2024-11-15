package main.java.com.yevhen.tkachenko.server.database.lab3.controller;

import com.google.gson.Gson;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.OutputStream;
import java.sql.SQLException;
import java.util.List;

import main.java.com.yevhen.tkachenko.server.database.lab3.model.Actor;
import main.java.com.yevhen.tkachenko.server.database.lab3.service.ActorService;

public class ActorController implements HttpHandler {
    private final ActorService actorService;
    private final Gson gson = new Gson();

    public ActorController(ActorService actorService) {
        this.actorService = actorService;
    }

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        exchange.getResponseHeaders().add("Access-Control-Allow-Origin", "*");
        exchange.getResponseHeaders().add("Access-Control-Allow-Methods", "GET, POST, OPTIONS");
        exchange.getResponseHeaders().add("Access-Control-Allow-Headers", "Content-Type");

        if ("OPTIONS".equalsIgnoreCase(exchange.getRequestMethod())) {
            exchange.sendResponseHeaders(204, -1);
            return;
        }

        if ("GET".equals(exchange.getRequestMethod())) {
            String queryParams = exchange.getRequestURI().getQuery();
            String filmIdParam = null;
            if (queryParams != null) {
                for (String param : queryParams.split("&")) {
                    String[] pair = param.split("=");
                    if (pair[0].equals("filmId")) {
                        filmIdParam = pair[1];
                        break;
                    }
                }
            }

            try {
                    List<Actor> actors = null;
                    if (filmIdParam != null) {
                        int filmId = Integer.parseInt(filmIdParam);
                        actors = actorService.getActorsByFilmId(filmId);
                    }
                    if (filmIdParam == null) {
                        actors = actorService.getAllActors();
                    }

                    String response = new Gson().toJson(actors);
                    exchange.sendResponseHeaders(200, response.getBytes().length);
                    OutputStream os = exchange.getResponseBody();
                    os.write(response.getBytes());
                    os.close();
            } catch (SQLException e) {
                exchange.sendResponseHeaders(500, 0);
            }
        }
    }
}