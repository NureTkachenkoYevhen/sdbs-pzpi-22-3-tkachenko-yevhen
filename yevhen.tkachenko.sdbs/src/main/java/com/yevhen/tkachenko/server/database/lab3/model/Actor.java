package main.java.com.yevhen.tkachenko.server.database.lab3.model;

import java.sql.Date;

public class Actor {
    private int actorId;
    private String actorFullName;
    private Date actorDate;
    private String nationality;
    private String gender;

    public Actor(int actorId, String actorFullName, Date actorDate, String nationality, String gender) {
        this.actorId = actorId;
        this.actorFullName = actorFullName;
        this.actorDate = actorDate;
        this.nationality = nationality;
        this.gender = gender;
    }

    public Actor() {
    }

    public int getActorId() {
        return actorId;
    }

    public void setActorId(int actorId) {
        this.actorId = actorId;
    }

    public String getActorFullName() {
        return actorFullName;
    }

    public void setActorFullName(String actorFullName) {
        this.actorFullName = actorFullName;
    }

    public Date getActorDate() {
        return actorDate;
    }

    public void setActorDate(Date actorDate) {
        this.actorDate = actorDate;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    @Override
    public String toString() {
        return "Actor{" +
                "actorId=" + actorId +
                ", actorFullName='" + actorFullName + '\'' +
                ", actorDate=" + actorDate +
                ", nationality='" + nationality + '\'' +
                ", gender='" + gender + '\'' +
                '}';
    }
}