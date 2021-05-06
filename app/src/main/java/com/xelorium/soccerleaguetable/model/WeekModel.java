package com.xelorium.soccerleaguetable.model;

import java.util.ArrayList;

public class WeekModel {

    private int weekCount;

    private ArrayList<MatchModel> matchObject;

    public WeekModel() {
    }

    public WeekModel(int weekCount, ArrayList<MatchModel> matchObject) {
        this.weekCount = weekCount;
        this.matchObject = matchObject;
    }

    public ArrayList<MatchModel> getMatchObject() {
        return matchObject;
    }

    public void setMatchObject(ArrayList<MatchModel> matchObject) {
        this.matchObject = matchObject;
    }

    public int getWeekCount() {
        return weekCount;
    }

    public void setWeekCount(int weekCount) {
        this.weekCount = weekCount;
    }


}
