package com.xelorium.soccerleaguetable;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.xelorium.soccerleaguetable.model.TeamModel;

import java.util.List;

@Dao
public interface TeamDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(List<TeamModel> teamModel);

    @Update
    void update(List<TeamModel> teamModel);

    @Delete
    void delete(List<TeamModel> teamModel);

    @Query("DELETE FROM team_table")
    void deleteAllTeams();

    @Query("SELECT * FROM team_table ORDER BY id ASC")
    LiveData<List<TeamModel>> getAllTeams();

}
