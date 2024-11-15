package main.java.com.yevhen.tkachenko.server.database.lab3.controller;

import com.google.gson.Gson;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import main.java.com.yevhen.tkachenko.server.database.lab3.model.ActorParticipate;
import main.java.com.yevhen.tkachenko.server.database.lab3.service.ActorParticipateService;

import java.io.IOException;
import java.io.OutputStream;
import java.sql.SQLException;
import java.util.List;

public class ActorParticipateController implements HttpHandler {
    private final ActorParticipateService actorParticipateService;
    private final Gson gson = new Gson();

    public ActorParticipateController(ActorParticipateService actorParticipateService) {
        this.actorParticipateService = actorParticipateService;
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

        if ("GET".equalsIgnoreCase(exchange.getRequestMethod())) {
            try {
                List<ActorParticipate> actors = actorParticipateService.getActorTotalFees();

                String jsonResponse = gson.toJson(actors);

                exchange.getResponseHeaders().add("Content-Type", "application/json");
                exchange.sendResponseHeaders(200, jsonResponse.getBytes().length);

                try (OutputStream os = exchange.getResponseBody()) {
                    os.write(jsonResponse.getBytes());
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
