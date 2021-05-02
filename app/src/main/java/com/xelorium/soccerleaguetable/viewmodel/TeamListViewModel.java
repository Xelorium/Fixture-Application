package com.xelorium.soccerleaguetable.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.xelorium.soccerleaguetable.TeamRepository;
import com.xelorium.soccerleaguetable.model.TeamModel;
import com.xelorium.soccerleaguetable.network.APIService;
import com.xelorium.soccerleaguetable.network.RetroInstance;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TeamListViewModel extends AndroidViewModel {

    private TeamRepository teamRepository;
    private LiveData<List<TeamModel>> getAllTeams;

    public TeamListViewModel(@NonNull Application application) {
        super(application);
        teamRepository = new TeamRepository(application);
        getAllTeams = teamRepository.getAllTeams();
    }

    public void insert(List<TeamModel> teamModel){
        teamRepository.insert(teamModel);
    }

    public LiveData<List<TeamModel>> getGetAllTeams() {
        return getAllTeams;
    }




}
