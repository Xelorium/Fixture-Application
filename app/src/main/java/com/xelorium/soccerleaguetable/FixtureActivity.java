package com.xelorium.soccerleaguetable;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.SnapHelper;

import com.xelorium.soccerleaguetable.adapter.WeekAdapter;
import com.xelorium.soccerleaguetable.databinding.ActivityFixtureBinding;
import com.xelorium.soccerleaguetable.model.FixtureModel;
import com.xelorium.soccerleaguetable.model.MatchModel;
import com.xelorium.soccerleaguetable.model.TeamModel;
import com.xelorium.soccerleaguetable.model.WeekModel;
import com.xelorium.soccerleaguetable.room.TeamRepository;
import com.xelorium.soccerleaguetable.viewmodel.FixtureViewModel;

import java.util.ArrayList;
import java.util.List;

public class FixtureActivity extends AppCompatActivity {

    private ActivityFixtureBinding binding;
    private FixtureViewModel viewModel;
    private WeekAdapter weekAdapter;
    private ArrayList<FixtureModel> fixtureList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityFixtureBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Intent intent = getIntent();
        fixtureList = intent.getParcelableArrayListExtra("FIXTURE_LIST");

        weekAdapter = new WeekAdapter(this, fixtureList);


        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        binding.rvFixtureWeek.setLayoutManager(linearLayoutManager);
        binding.rvFixtureWeek.setHasFixedSize(true);
        SnapHelper snapHelper = new PagerSnapHelper();
        binding.rvFixtureWeek.setAdapter(weekAdapter);
        //notify
        snapHelper.attachToRecyclerView(binding.rvFixtureWeek);


    }

}