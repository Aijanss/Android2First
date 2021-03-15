package com.example.android2first;

import android.app.Application;

import androidx.room.Room;

import com.example.android2first.Prefs.Prefs;
import com.example.android2first.room.AppData;

public class App extends Application {
    private static AppData appDataBase;
    private static  Prefs prefs;

    public static Prefs getPrefs() {
        return prefs;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        appDataBase = Room.databaseBuilder(this, AppData.class,
                "database").allowMainThreadQueries().build();
        prefs =new Prefs(this);

    }

    public static AppData getAppDataBase() {

        return appDataBase;
    }


    }



