package com.xelorium.soccerleaguetable;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.xelorium.soccerleaguetable.adapter.TeamListAdapter;
import com.xelorium.soccerleaguetable.databinding.ActivityMainBinding;
import com.xelorium.soccerleaguetable.model.TeamModel;
import com.xelorium.soccerleaguetable.network.APIService;
import com.xelorium.soccerleaguetable.network.RetroInstance;
import com.xelorium.soccerleaguetable.room.TeamRepository;
import com.xelorium.soccerleaguetable.viewmodel.TeamListViewModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private List<TeamModel> teamModelList;
    private TeamListAdapter adapter;
    private TeamListViewModel viewModel;
    private TeamRepository teamRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        binding.rvTeamList.setLayoutManager(linearLayoutManager);
        adapter = new TeamListAdapter(this, teamModelList);
        binding.rvTeamList.setAdapter(adapter);

        teamRepository=new TeamRepository(getApplication());




        viewModel = new ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory.getInstance((this.getApplication()))).get(TeamListViewModel.class);

        makeApiCall();

        viewModel.getGetAllTeams().observe(this, new Observer<List<TeamModel>>() {
            @Override
            public void onChanged(List<TeamModel> list) {

                if (list != null){
                    teamModelList = list;
                    binding.rvTeamList.setAdapter(adapter);
                    adapter.getAllTeams(teamModelList);
                    binding.tvMainNoData.setVisibility(View.GONE);
                } else {
                    binding.tvMainNoData.setVisibility(View.VISIBLE);
                }


            }
        });


        binding.fabDrawFixture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, FixtureActivity.class));
            }
        });

    }

    private void makeApiCall() {
        APIService apiService = RetroInstance.getRetroClient().create(APIService.class);
        Call<List<TeamModel>> call = apiService.getTeamList();
        call.enqueue(new Callback<List<TeamModel>>() {
            @Override
            public void onResponse(Call<List<TeamModel>> call, Response<List<TeamModel>> response) {
                teamRepository.deleteAllTeams();
                teamRepository.insert(response.body());
            }

            @Override
            public void onFailure(Call<List<TeamModel>> call, Throwable t) {
//                teamList.postValue(null);

            }
        });
    }

}
