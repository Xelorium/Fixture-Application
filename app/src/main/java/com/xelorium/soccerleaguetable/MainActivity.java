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
import com.xelorium.soccerleaguetable.model.FixtureModel;
import com.xelorium.soccerleaguetable.model.MatchModel;
import com.xelorium.soccerleaguetable.model.TeamModel;
import com.xelorium.soccerleaguetable.network.APIService;
import com.xelorium.soccerleaguetable.network.RetroInstance;
import com.xelorium.soccerleaguetable.room.TeamRepository;
import com.xelorium.soccerleaguetable.viewmodel.TeamListViewModel;

import java.util.ArrayList;
import java.util.LinkedList;
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
    private List<TeamModel> tempList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.pbTeamList.setVisibility(View.VISIBLE);

        teamModelList = new ArrayList<>();

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        binding.rvTeamList.setLayoutManager(linearLayoutManager);
        adapter = new TeamListAdapter(this, teamModelList);

        teamRepository = new TeamRepository(getApplication());


        tempList = new ArrayList<>();

        ArrayList<FixtureModel> fixtureList = new ArrayList<>();

        viewModel = new ViewModelProvider(
                this, ViewModelProvider.AndroidViewModelFactory.getInstance((this.getApplication()))).get(TeamListViewModel.class);

        makeApiCall();

        viewModel.getGetAllTeams().observe(this, new Observer<List<TeamModel>>() {
            @Override
            public void onChanged(List<TeamModel> list) {

                if (list != null) {
                    binding.pbTeamList.setVisibility(View.VISIBLE);
                    teamModelList = list;

                    fixtureList.clear();


                    binding.pbTeamList.setVisibility(View.GONE);
                    binding.rvTeamList.setAdapter(adapter);
                    adapter.getAllTeams(teamModelList);
                    binding.tvMainNoData.setVisibility(View.GONE);
                } else {
                    binding.tvMainNoData.setVisibility(View.VISIBLE);
                    binding.pbTeamList.setVisibility(View.GONE);
                }


            }
        });

        binding.fabDrawFixture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FixtureGenerator<String> fixtureGenerator = new FixtureGenerator<>();

                List<String> teams = new LinkedList<String>();

                for (int i = 0; i < teamModelList.size(); i++) {
                    teams.add(teamModelList.get(i).getName());
                }

                String result = "";

                List<List<MatchModel<String>>> rounds = fixtureGenerator.getFixtures(teams, true);


                for (int i = 0; i < rounds.size(); i++) {

                    result = "";

                    int weekCount = i;
                    List<MatchModel<String>> round = rounds.get(i);

                    for (MatchModel<String> fixture : round) {

                        if ((!fixture.getHomeTeamName().equals("") && !fixture.getAwayTeamName().equals(""))
                                && (!fixture.getHomeTeamName().equals(fixture.getAwayTeamName()))) {

                            result += fixture.getHomeTeamName() + " - " + fixture.getAwayTeamName() + "\n";

                        }


                    }

                    fixtureList.add(new FixtureModel(weekCount, result));

                }

                Intent intent = new Intent(MainActivity.this, FixtureActivity.class);
                intent.putExtra("FIXTURE_LIST", fixtureList);

                if (!fixtureList.isEmpty()) {
                    startActivity(intent);
                    fixtureList.clear();
                    overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);

                }

            }
        });

    }

    private void makeApiCall() {
        APIService apiService = RetroInstance.getRetroClient().create(APIService.class);
        Call<List<TeamModel>> call = apiService.getTeamList();
        call.enqueue(new Callback<List<TeamModel>>() {
            @Override
            public void onResponse(Call<List<TeamModel>> call, Response<List<TeamModel>> response) {

                if (response.isSuccessful()) {
                    teamRepository.deleteAllTeams();

                    //Adding single team to the team list for testing odd and even numbers
                    //response.body().add(new TeamModel("21", "Test Team"));

                    if (response.body().size() % 2 != 0) {
                        tempList.add(new TeamModel("", ""));
                        teamRepository.insert(response.body());
                        teamRepository.insert(tempList);
                        binding.fabDrawFixture.setVisibility(View.VISIBLE);

                    } else {
                        teamRepository.insert(response.body());
                        binding.fabDrawFixture.setVisibility(View.VISIBLE);

                    }

                } else {
                    binding.pbTeamList.setVisibility(View.GONE);
                    binding.tvMainNoData.setVisibility(View.VISIBLE);
                }

            }

            @Override
            public void onFailure(Call<List<TeamModel>> call, Throwable t) {
                binding.pbTeamList.setVisibility(View.GONE);
                binding.tvMainNoData.setVisibility(View.VISIBLE);
            }
        });

    }

}
