package com.xelorium.soccerleaguetable.room;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.xelorium.soccerleaguetable.model.TeamModel;
import com.xelorium.soccerleaguetable.room.TeamDao;
import com.xelorium.soccerleaguetable.room.TeamDatabase;

import java.util.List;

public class TeamRepository {
    private LiveData<List<TeamModel>> allTeams;
    private TeamDatabase database;

    public TeamRepository(Application application){
        database = TeamDatabase.getInstance(application);
        allTeams = database.teamDao().getAllTeams();
    }

    public void insert(List<TeamModel> teamModel){
        new InsertTeamAsyncTask(database).execute(teamModel);
    }

    public void update(List<TeamModel> teamModel){
        new UpdateTeamAsyncTask(database).execute(teamModel);
    }

    public void delete(List<TeamModel> teamModel){
        new DeleteTeamAsyncTask(database).execute(teamModel);
    }

    public void deleteAllTeams(){
        new DeleteAllTeamsAsyncTask(database).execute();

    }

    public LiveData<List<TeamModel>> getAllTeams(){
        return allTeams;
    }

    private static class InsertTeamAsyncTask extends AsyncTask<List<TeamModel>, Void, Void>{
        private TeamDao teamDao;

        private InsertTeamAsyncTask(TeamDatabase teamDatabase){
            teamDao = teamDatabase.teamDao();
        }

        @Override
        protected Void doInBackground(List<TeamModel>... teamModels) {
            teamDao.insert(teamModels[0]);
            return null;
        }
    }

    private static class UpdateTeamAsyncTask extends AsyncTask<List<TeamModel>, Void, Void>{
        private TeamDao teamDao;

        private UpdateTeamAsyncTask(TeamDatabase teamDatabase){
            teamDao = teamDatabase.teamDao();
        }

        @Override
        protected Void doInBackground(List<TeamModel>... teamModels) {
            teamDao.update(teamModels[0]);
            return null;
        }
    }

    private static class DeleteTeamAsyncTask extends AsyncTask<List<TeamModel>, Void, Void>{
        private TeamDao teamDao;

        private DeleteTeamAsyncTask(TeamDatabase teamDatabase){
            teamDao = teamDatabase.teamDao();
        }

        @Override
        protected Void doInBackground(List<TeamModel>... teamModels) {
            teamDao.delete(teamModels[0]);
            return null;
        }
    }

    private static class DeleteAllTeamsAsyncTask extends AsyncTask<List<TeamModel>, Void, Void>{
        private TeamDao teamDao;

        private DeleteAllTeamsAsyncTask(TeamDatabase teamDatabase){
            teamDao = teamDatabase.teamDao();
        }

        @Override
        protected Void doInBackground(List<TeamModel>... teamModels) {
            teamDao.deleteAllTeams();
            return null;
        }
    }

}
