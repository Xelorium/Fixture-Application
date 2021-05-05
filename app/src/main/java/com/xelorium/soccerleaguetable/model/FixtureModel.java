package com.xelorium.soccerleaguetable.model;

import android.os.Parcel;
import android.os.Parcelable;

public class FixtureModel implements Parcelable {

    private int weekCount;
    private String matchName;

    public FixtureModel() {
    }

    public FixtureModel(int weekCount, String matchName) {
        this.weekCount = weekCount;
        this.matchName = matchName;
    }

    public int getWeekCount() {
        return weekCount;
    }

    public void setWeekCount(int weekCount) {
        this.weekCount = weekCount;
    }

    public String getMatchName() {
        return matchName;
    }

    public void setMatchName(String matchName) {
        this.matchName = matchName;
    }

    protected FixtureModel(Parcel in) {
        weekCount = in.readInt();
        matchName = in.readString();
    }

    public static final Creator<FixtureModel> CREATOR = new Creator<FixtureModel>() {
        @Override
        public FixtureModel createFromParcel(Parcel in) {
            return new FixtureModel(in);
        }

        @Override
        public FixtureModel[] newArray(int size) {
            return new FixtureModel[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(weekCount);
        dest.writeString(matchName);
    }
}
