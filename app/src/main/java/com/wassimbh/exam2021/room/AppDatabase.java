package com.wassimbh.exam2021.room;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.wassimbh.exam2021.entities.Player;

@Database(entities = {Player.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract PlayerDao player_Dao();
}
