package com.xelorium.soccerleaguetable.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.xelorium.soccerleaguetable.model.TeamModel;
import com.xelorium.soccerleaguetable.room.TeamRepository;

import java.util.List;

public class TeamListViewModel extends AndroidViewModel {

    private TeamRepository teamRepository;
    private LiveData<List<TeamModel>> getAllTeams;

    public TeamListViewModel(@NonNull Application application) {
        super(application);
        teamRepository = new TeamRepository(application);
        getAllTeams = teamRepository.getAllTeams();
    }

    public void insert(List<TeamModel> teamModel) {
        teamRepository.insert(teamModel);
    }

    public LiveData<List<TeamModel>> getGetAllTeams() {
        return getAllTeams;
    }


}
