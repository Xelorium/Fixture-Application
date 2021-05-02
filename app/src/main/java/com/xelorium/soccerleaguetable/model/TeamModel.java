package com.xelorium.soccerleaguetable.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

@Entity(tableName = "team_table")
public class TeamModel {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @SerializedName("squad_position")
    private String position;

    @SerializedName("squad_name")
    private String name;

    public TeamModel(String position, String name) {
        this.position = position;
        this.name = name;
    }

    public TeamModel() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
