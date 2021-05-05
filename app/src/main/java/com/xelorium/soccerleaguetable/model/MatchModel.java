package com.xelorium.soccerleaguetable.model;

import java.util.ArrayList;

public class MatchModel <T extends Object>{

    private T homeTeamName;
    private T awayTeamName;


    public MatchModel(T homeTeamName, T awayTeamName) {
        this.homeTeamName = homeTeamName;
        this.awayTeamName = awayTeamName;
    }

    public T getHomeTeamName() {
        return homeTeamName;
    }

    public void setHomeTeamName(T homeTeamName) {
        this.homeTeamName = homeTeamName;
    }

    public T getAwayTeamName() {
        return awayTeamName;
    }

    public void setAwayTeamName(T awayTeamName) {
        this.awayTeamName = awayTeamName;
    }
}
