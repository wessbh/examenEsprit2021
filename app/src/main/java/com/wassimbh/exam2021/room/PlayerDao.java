package com.wassimbh.exam2021.room;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.wassimbh.exam2021.entities.Player;

import java.util.List;

@Dao
public interface PlayerDao {

    @Query("SELECT * FROM player")
    List<Player> getAllPlayers();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void addPlayer(Player player);

    @Delete
    void deletePlayer(Player player);
}
