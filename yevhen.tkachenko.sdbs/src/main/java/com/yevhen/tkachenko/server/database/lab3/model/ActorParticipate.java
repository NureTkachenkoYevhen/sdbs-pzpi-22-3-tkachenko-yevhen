package main.java.com.yevhen.tkachenko.server.database.lab3.model;

import java.math.BigDecimal;

public class ActorParticipate {
    private Actor actor;
    private BigDecimal feeNum;

    public void setFeeNum(BigDecimal feeNum) {
        this.feeNum = feeNum;
    }

    public BigDecimal getFeeNum() {
        return feeNum;
    }

    public void setActor(Actor actor) {
        this.actor = actor;
    }

    public Actor getActor() {
        return actor;
    }
}
