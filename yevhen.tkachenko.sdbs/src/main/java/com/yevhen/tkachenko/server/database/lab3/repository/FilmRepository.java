package main.java.com.yevhen.tkachenko.server.database.lab3.repository;

import main.java.com.yevhen.tkachenko.server.database.lab3.model.Film;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FilmRepository {
    private final Connection connection;

    public FilmRepository(Connection connection) {
        this.connection = connection;
    }

    public List<Film> getAllFilms() throws SQLException {
        String query = "SELECT * FROM film";
        List<Film> films = new ArrayList<>();
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                films.add(new Film(
                        rs.getInt("film_id"),
                        rs.getString("film_name"),
                        rs.getInt("filming_percentage"),
                        rs.getString("genre"),
                        rs.getTime("duration"),
                        rs.getInt("rating"),
                        rs.getDate("film_date"),
                        rs.getBigDecimal("budget_num"),
                        rs.getBigDecimal("box_office_num")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return films;
    }

    public void addFilmToActor(Film film) {
        String query = "CALL add_film(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (CallableStatement stmt = connection.prepareCall(query)) {
            stmt.setString(1, film.getFilmName());
            stmt.setInt(2, film.getFilmingPercentage());
            stmt.setString(3, film.getGenre());
            stmt.setTime(4, film.getDuration());
            stmt.setInt(5, film.getRating());
            stmt.setDate(6, film.getFilmDate());
            stmt.setBigDecimal(7, film.getBudgetNum());
            stmt.setBigDecimal(8, film.getBoxOfficeNum());
            stmt.setInt(9,film.getActorParticipate().getActor().getActorId());
            stmt.setBigDecimal(10,film.getActorParticipate().getFeeNum());

            stmt.execute();
        } catch (SQLException e) {
            if ("P0001".equals(e.getSQLState())) {
                System.out.println("Помилка: " + e.getMessage());
            } else {
                e.printStackTrace();
            }
        }
    }

    public Film getTopFilmByYear(int year)throws SQLException {
        String query = "SELECT * FROM get_top_film_by_year("+year+");";
        Film film = new Film();
        try (Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                film.setFilmName(rs.getString("film_name"));
                film.setFilmDate(rs.getDate("film_date"));
                film.setBoxOfficeNum(rs.getBigDecimal("box_office_num"));
            }
        }
        return film;
    }
}
