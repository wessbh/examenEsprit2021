package com.wassimbh.exam2021.entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Objects;

@Entity
public class Player {
    @PrimaryKey
    int id;
    @ColumnInfo(name = "username")
    String userName;

    @ColumnInfo(name = "drawableID")
    int drawableID;

    @ColumnInfo(name = "different")
    boolean different;

    public Player(int id, String userName, int drawableID) {
        this.id = id;
        this.userName = userName;
        this.drawableID = drawableID;
        different = false;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getDrawableID() {
        return drawableID;
    }

    public void setDrawableID(int drawableID) {
        this.drawableID = drawableID;
    }

    public boolean isDifferent() {
        return different;
    }

    public void setDifferent(boolean different) {
        this.different = different;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Player player = (Player) o;
        return id == player.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userName, drawableID);
    }

    @Override
    public String toString() {
        return "Player{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", drawableID=" + drawableID +
                ", different=" + different +
                '}';
    }
}
