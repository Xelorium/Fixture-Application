package com.xelorium.soccerleaguetable.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.xelorium.soccerleaguetable.model.TeamModel;
import com.xelorium.soccerleaguetable.network.APIService;
import com.xelorium.soccerleaguetable.network.RetroInstance;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TeamListViewModel extends ViewModel {

    private MutableLiveData<List<TeamModel>> teamList;

    public TeamListViewModel() {
        teamList = new MutableLiveData<>();
    }

    public MutableLiveData<List<TeamModel>> getTeamListObserver() {
        return teamList;
    }

    public void makeApiCall() {
        APIService apiService = RetroInstance.getRetroClient().create(APIService.class);
        Call<List<TeamModel>> call = apiService.getTeamList();
        call.enqueue(new Callback<List<TeamModel>>() {
            @Override
            public void onResponse(Call<List<TeamModel>> call, Response<List<TeamModel>> response) {
                teamList.postValue(response.body());
            }

            @Override
            public void onFailure(Call<List<TeamModel>> call, Throwable t) {
                teamList.postValue(null);

            }
        });
    }


}
