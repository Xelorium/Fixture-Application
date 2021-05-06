package com.xelorium.soccerleaguetable.room;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.xelorium.soccerleaguetable.model.TeamModel;

@Database(entities = {TeamModel.class}, version = 1)
public abstract class TeamDatabase extends RoomDatabase {

    private static TeamDatabase instance;
    private static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateDbAsyncTask(instance).execute();
        }
    };

    public static synchronized TeamDatabase getInstance(Context context) {

        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    TeamDatabase.class, "team_database")
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallback)
                    .build();
        }
        return instance;
    }

    public abstract TeamDao teamDao();

    private static class PopulateDbAsyncTask extends AsyncTask<Void, Void, Void> {
        private TeamDao teamDao;

        private PopulateDbAsyncTask(TeamDatabase db) {
            teamDao = db.teamDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            teamDao.deleteAllTeams();
            return null;
        }
    }

}