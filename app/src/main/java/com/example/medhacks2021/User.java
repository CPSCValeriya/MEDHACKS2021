package com.example.medhacks2021;

import android.content.ContentResolver;
import android.database.Cursor;
import android.provider.ContactsContract;
import android.util.Log;

public class User {



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getPoints() {
        return points;
    }

    public void setPoints(long points) {
        this.points = points;
    }

    private String name;
    private long points;

    public User(String name, Integer points) {
        this.name = name;
        this.points = points;
    }


    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", points=" + points +
                '}';
    }


}
