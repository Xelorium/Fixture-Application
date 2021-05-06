package com.xelorium.soccerleaguetable;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.SnapHelper;

import com.xelorium.soccerleaguetable.adapter.WeekAdapter;
import com.xelorium.soccerleaguetable.databinding.ActivityFixtureBinding;
import com.xelorium.soccerleaguetable.model.FixtureModel;

import java.util.ArrayList;

public class FixtureActivity extends AppCompatActivity {

    private ActivityFixtureBinding binding;
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
        weekAdapter.notifyDataSetChanged();
        snapHelper.attachToRecyclerView(binding.rvFixtureWeek);

    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }
}