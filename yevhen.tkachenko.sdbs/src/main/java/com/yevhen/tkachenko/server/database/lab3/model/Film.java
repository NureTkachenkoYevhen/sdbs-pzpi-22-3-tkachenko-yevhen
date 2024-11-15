package main.java.com.yevhen.tkachenko.server.database.lab3.model;

import java.math.BigDecimal;
import java.sql.Time;
import java.sql.Date;

public class Film {
    private int filmId;
    private String filmName;
    private int filmingPercentage;
    private String genre;
    private Time duration;
    private int rating;
    private Date filmDate;
    private BigDecimal budgetNum;
    private BigDecimal boxOfficeNum;
    private ActorParticipate actorParticipate;

    public Film(int filmId, String filmName, int filmingPercentage, String genre, Time duration,
                int rating, Date filmDate, BigDecimal budgetNum, BigDecimal boxOfficeNum) {
        this.filmId = filmId;
        this.filmName = filmName;
        this.filmingPercentage = filmingPercentage;
        this.genre = genre;
        this.duration = duration;
        this.rating = rating;
        this.filmDate = filmDate;
        this.budgetNum = budgetNum;
        this.boxOfficeNum = boxOfficeNum;
    }

    public int getFilmId() {
        return filmId;
    }

    public void setFilmId(int filmId) {
        this.filmId = filmId;
    }

    public String getFilmName() {
        return filmName;
    }

    public void setFilmName(String filmName) {
        this.filmName = filmName;
    }

    public int getFilmingPercentage() {
        return filmingPercentage;
    }

    public void setFilmingPercentage(int filmingPercentage) {
        this.filmingPercentage = filmingPercentage;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public Time getDuration() {
        return duration;
    }

    public void setDuration(Time duration) {
        this.duration = duration;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public Date getFilmDate() {
        return filmDate;
    }

    public void setFilmDate(Date filmDate) {
        this.filmDate = filmDate;
    }

    public BigDecimal getBudgetNum() {
        return budgetNum;
    }

    public void setBudgetNum(BigDecimal budgetNum) {
        this.budgetNum = budgetNum;
    }

    public BigDecimal getBoxOfficeNum() {
        return boxOfficeNum;
    }

    public void setBoxOfficeNum(BigDecimal boxOfficeNum) {
        this.boxOfficeNum = boxOfficeNum;
    }

    public ActorParticipate getActorParticipate() {
        return actorParticipate;
    }

    public Film(){}

    public static Film generateFilmForActor(String json) throws IllegalArgumentException {
        Film filmResult = new Film();
        filmResult.actorParticipate = new ActorParticipate();

        json = json.trim().replaceAll("[{}\"]", "");
        String[] keyValuePairs = json.split(",");

        for (String pair : keyValuePairs) {
            String[] keyValue = pair.split(":");
            String key = keyValue[0].trim();
            String value = keyValue.length == 2 ? keyValue[1].trim() : keyValue[1] + ":" + keyValue[2] + ":" + keyValue[3];

            if (key.equals("filmName")) {
                filmResult.filmName = value;
            }
            if (key.equals("filmingPercentage")) {
                int filmingPercentage = Integer.parseInt(value);
                if (filmingPercentage < 0 || filmingPercentage > 100) {
                    throw new IllegalArgumentException("filmingPercentage повинен бути від 0 до 100");
                }
                filmResult.filmingPercentage = filmingPercentage;
            }
            if (key.equals("genre")) {
                filmResult.genre = value;
            }
            if (key.equals("duration")) {
                filmResult.duration = Time.valueOf(value);
            }
            if (key.equals("rating")) {
                int rating = Integer.parseInt(value);
                if (rating < 0 || rating > 10) {
                    throw new IllegalArgumentException("rating повинен бути від 0 до 10");
                }
                filmResult.rating = rating;
            }
            if (key.equals("filmDate")) {
                filmResult.filmDate = Date.valueOf(value);
            }
            if (key.equals("budgetNum")) {
                filmResult.budgetNum = new BigDecimal(value);
            }
            if (key.equals("boxOfficeNum")) {
                filmResult.boxOfficeNum = new BigDecimal(value);
            }
            if (key.equals("actorId")) {
                Actor actor = new Actor();
                actor.setActorId(Integer.parseInt(value));
                filmResult.actorParticipate.setActor(actor);
            }
            if (key.equals("feeNum")) {
                filmResult.actorParticipate.setFeeNum(new BigDecimal(value));
            }
        }
        return filmResult;
    }



    @Override
    public String toString() {
        return "Film{" +
                "filmId=" + filmId +
                ", filmName='" + filmName + '\'' +
                ", filmingPercentage=" + filmingPercentage +
                ", genre='" + genre + '\'' +
                ", duration=" + duration +
                ", rating=" + rating +
                ", filmDate=" + filmDate +
                ", budgetNum=" + budgetNum +
                ", boxOfficeNum=" + boxOfficeNum +
                '}';
    }
}
