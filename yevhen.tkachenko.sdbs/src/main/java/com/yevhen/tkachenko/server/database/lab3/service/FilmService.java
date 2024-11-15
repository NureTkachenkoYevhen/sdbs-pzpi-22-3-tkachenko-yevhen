package main.java.com.yevhen.tkachenko.server.database.lab3.service;

import main.java.com.yevhen.tkachenko.server.database.lab3.model.Film;
import main.java.com.yevhen.tkachenko.server.database.lab3.repository.FilmRepository;

import java.sql.SQLException;
import java.util.List;

public class FilmService {
    private final FilmRepository filmRepository;

    public FilmService(FilmRepository filmRepository) {
        this.filmRepository = filmRepository;
    }

    public List<Film> getAllFilms() throws SQLException {
        return filmRepository.getAllFilms();
    }

    public void addFilm(Film film) {
        filmRepository.addFilmToActor(film);
    }

    public Film getTopFilmByYear(int year) throws SQLException {
        return filmRepository.getTopFilmByYear(year);
    }
}

