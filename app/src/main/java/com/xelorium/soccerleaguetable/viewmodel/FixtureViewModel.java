package com.xelorium.soccerleaguetable.viewmodel;

import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.xelorium.soccerleaguetable.model.FixtureModel;
import com.xelorium.soccerleaguetable.model.MatchModel;
import com.xelorium.soccerleaguetable.model.WeekModel;

import java.util.ArrayList;

public class FixtureViewModel extends AndroidViewModel {

    private MutableLiveData<ArrayList<FixtureModel>> weekLiveData;
    private ArrayList<FixtureModel> weekList;
    private ArrayList<MatchModel> matchList;

    public FixtureViewModel(@NonNull Application application){
        super(application);
        weekLiveData = new MutableLiveData<>();

        init();
    }

    public MutableLiveData<ArrayList<FixtureModel>> getWeekLiveData(){
        return weekLiveData;
    }

    public void init(){
        populateList();
        weekLiveData.setValue(weekList);
    }

    public void populateList(){

        WeekModel weekModel = new WeekModel();

        ArrayList<MatchModel> matchList = new ArrayList<>();
        weekList = new ArrayList<>();

    }

}
