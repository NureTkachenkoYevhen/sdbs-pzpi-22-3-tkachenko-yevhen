package main.java.com.yevhen.tkachenko.server.database.lab3.controller;

import com.google.gson.Gson;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import main.java.com.yevhen.tkachenko.server.database.lab3.model.Film;
import main.java.com.yevhen.tkachenko.server.database.lab3.service.FilmService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FilmController implements HttpHandler {
    private final FilmService filmService;
    private final Gson gson = new Gson();

    public FilmController(FilmService filmService) {
        this.filmService = filmService;
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

        if ("POST".equalsIgnoreCase(exchange.getRequestMethod())) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(exchange.getRequestBody(), StandardCharsets.UTF_8));
            StringBuilder jsonBody = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                jsonBody.append(line);
            }

            try {
                Film film = Film.generateFilmForActor(jsonBody.toString());

                filmService.addFilm(film);


                Map<String, Object> responseMap = new HashMap<>();
                responseMap.put("message", "Added successfully");

                String jsonResponse = gson.toJson(responseMap);

                exchange.getResponseHeaders().add("Content-Type", "application/json");
                exchange.sendResponseHeaders(200, jsonResponse.getBytes().length);

                try (OutputStream os = exchange.getResponseBody()) {
                    os.write(jsonResponse.getBytes());
                }
            } catch (IllegalArgumentException e) {
                Map<String, Object> responseMap = new HashMap<>();
                responseMap.put("error", e.getMessage());

                String errorResponse = gson.toJson(responseMap);
                exchange.getResponseHeaders().add("Content-Type", "application/json");
                exchange.sendResponseHeaders(400, errorResponse.getBytes().length);

                try (OutputStream os = exchange.getResponseBody()) {
                    os.write(errorResponse.getBytes());
                }
            } finally {
                exchange.getResponseBody().close();
            }
        }

        if ("GET".equalsIgnoreCase(exchange.getRequestMethod())) {
            String queryParams = exchange.getRequestURI().getQuery();
            String year = null;
            if (queryParams != null) {
                for (String param : queryParams.split("&")) {
                    String[] pair = param.split("=");
                    if (pair[0].equals("year")) {
                        year = pair[1];
                        break;
                    }
                }
            }
            try {
                if(year == null) {

                    List<Film> films = filmService.getAllFilms();

                    String jsonResponse = gson.toJson(films);

                    exchange.getResponseHeaders().add("Content-Type", "application/json");
                    exchange.sendResponseHeaders(200, jsonResponse.getBytes().length);

                    try (OutputStream os = exchange.getResponseBody()) {
                        os.write(jsonResponse.getBytes());
                    }
                }
                if(year != null){
                    Film film = filmService.getTopFilmByYear(Integer.parseInt(year));

                    String jsonResponse = gson.toJson(film);

                    exchange.getResponseHeaders().add("Content-Type", "application/json");
                    exchange.sendResponseHeaders(200, jsonResponse.getBytes().length);

                    try (OutputStream os = exchange.getResponseBody()) {
                        os.write(jsonResponse.getBytes());
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}