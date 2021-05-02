package com.xelorium.soccerleaguetable;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.xelorium.soccerleaguetable.databinding.ActivityFixtureBinding;

public class FixtureActivity extends AppCompatActivity {

    private ActivityFixtureBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityFixtureBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


    }
}