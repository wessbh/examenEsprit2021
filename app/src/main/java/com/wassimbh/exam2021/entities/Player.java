package com.wassimbh.exam2021.entities;

public class Player {
    int id;
    String userName;
    int drawableID;

    public Player(int id, String userName, int drawableID) {
        this.id = id;
        this.userName = userName;
        this.drawableID = drawableID;
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
}
