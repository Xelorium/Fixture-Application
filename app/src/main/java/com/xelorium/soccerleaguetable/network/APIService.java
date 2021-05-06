package com.xelorium.soccerleaguetable.network;

import com.xelorium.soccerleaguetable.model.TeamModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;

public interface APIService {

    @Headers({
            "x-rapidapi-key: 81562325e2mshbd0d89553a475b7p15c935jsn5518849f9136",
            "x-rapidapi-host: football98.p.rapidapi.com"
    })
    @GET("/premierleague/squads")
    Call<List<TeamModel>> getTeamList();

}
