package com.xelorium.soccerleaguetable;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.SnapHelper;

import android.os.Bundle;

import com.xelorium.soccerleaguetable.adapter.MatchAdapter;
import com.xelorium.soccerleaguetable.databinding.ActivityFixtureBinding;
import com.xelorium.soccerleaguetable.model.MatchModel;
import com.xelorium.soccerleaguetable.room.TeamRepository;
import com.xelorium.soccerleaguetable.viewmodel.TeamListViewModel;

import java.util.ArrayList;

public class FixtureActivity extends AppCompatActivity {

    private ActivityFixtureBinding binding;
    private ArrayList<MatchModel> matchList;
    private TeamListViewModel viewModel;
    private MatchAdapter adapter;
    private TeamRepository teamRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityFixtureBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        matchList = new ArrayList<>();

        matchList.add(new MatchModel(1));
        matchList.add(new MatchModel(2));
        matchList.add(new MatchModel(3));
        matchList.add(new MatchModel(4));
        matchList.add(new MatchModel(5));
        matchList.add(new MatchModel(6));

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        SnapHelper snapHelper = new PagerSnapHelper();
        binding.rvFixtureWeek.setLayoutManager(linearLayoutManager);
        binding.rvFixtureWeek.setHasFixedSize(true);
        adapter = new MatchAdapter(this, matchList);
        snapHelper.attachToRecyclerView(binding.rvFixtureWeek);

        binding.rvFixtureWeek.setAdapter(adapter);



    }
}