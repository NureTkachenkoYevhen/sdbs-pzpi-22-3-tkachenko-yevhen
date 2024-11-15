package main.java.com.yevhen.tkachenko.server.database.lab3;

import com.sun.net.httpserver.HttpServer;
import main.java.com.yevhen.tkachenko.server.database.lab3.config.DatabaseConnection;
import main.java.com.yevhen.tkachenko.server.database.lab3.controller.ActorController;
import main.java.com.yevhen.tkachenko.server.database.lab3.controller.ActorParticipateController;
import main.java.com.yevhen.tkachenko.server.database.lab3.controller.FilmController;
import main.java.com.yevhen.tkachenko.server.database.lab3.model.Film;
import main.java.com.yevhen.tkachenko.server.database.lab3.repository.ActorParticipateRepository;
import main.java.com.yevhen.tkachenko.server.database.lab3.repository.ActorRepository;
import main.java.com.yevhen.tkachenko.server.database.lab3.repository.FilmRepository;
import main.java.com.yevhen.tkachenko.server.database.lab3.service.ActorParticipateService;
import main.java.com.yevhen.tkachenko.server.database.lab3.service.ActorService;
import main.java.com.yevhen.tkachenko.server.database.lab3.service.FilmService;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.InetSocketAddress;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Time;

public class Main {
    public static void main(String[] args) {
        try {
            Connection connection = DatabaseConnection.getConnection();

            FilmRepository filmRepository = new FilmRepository(connection);
            ActorRepository actorRepository = new ActorRepository(connection);
            ActorParticipateRepository actorParticipateRepository = new ActorParticipateRepository(connection);

            FilmService filmService = new FilmService(filmRepository);
            ActorService actorService = new ActorService(actorRepository);
            ActorParticipateService actorParticipateService = new ActorParticipateService(actorParticipateRepository);

            HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);

            server.createContext("/films", new FilmController(filmService));
            server.createContext("/actors", new ActorController(actorService));
            server.createContext("/actors-participate", new ActorParticipateController(actorParticipateService));

            server.setExecutor(null);
            server.start();

            System.out.println("Server is running on port 8080...");

        } catch (IOException | SQLException e) {
            e.printStackTrace();
        }
    }
}