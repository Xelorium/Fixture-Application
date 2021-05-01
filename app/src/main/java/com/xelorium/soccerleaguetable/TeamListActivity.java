package com.xelorium.soccerleaguetable;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.xelorium.soccerleaguetable.adapter.TeamListAdapter;
import com.xelorium.soccerleaguetable.databinding.ActivityTeamListBinding;
import com.xelorium.soccerleaguetable.model.TeamModel;
import com.xelorium.soccerleaguetable.viewmodel.TeamListViewModel;

import java.util.List;

public class TeamListActivity extends AppCompatActivity {

    private ActivityTeamListBinding binding;
    private List<TeamModel> teamModelList;
    private TeamListAdapter adapter;
    private TeamListViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityTeamListBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        binding.rvTeamList.setLayoutManager(linearLayoutManager);
        adapter = new TeamListAdapter(this, teamModelList);
        binding.rvTeamList.setAdapter(adapter);


        viewModel = new ViewModelProvider(this).get(TeamListViewModel.class);

        viewModel.getTeamListObserver().observe(this, new Observer<List<TeamModel>>() {
            @Override
            public void onChanged(List<TeamModel> teamModels) {
                if (teamModels != null) {
                    teamModelList = teamModels;
                    adapter.setTeamList(teamModels);
                    binding.tvMainNoData.setVisibility(View.GONE);
                } else {
                    binding.tvMainNoData.setVisibility(View.VISIBLE);
                }
            }
        });

        viewModel.makeApiCall();

    }
}