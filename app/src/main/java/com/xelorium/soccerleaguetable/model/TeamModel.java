package com.xelorium.soccerleaguetable.model;

import com.google.gson.annotations.SerializedName;

public class TeamModel {

    @SerializedName("squad_position")
    private String id;

    @SerializedName("squad_name")
    private String name;

    public TeamModel(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
