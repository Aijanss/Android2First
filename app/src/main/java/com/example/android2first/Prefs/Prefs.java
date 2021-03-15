package com.example.android2first.Prefs;

import android.content.Context;
import android.content.SharedPreferences;

import java.io.Serializable;

public class Prefs implements Serializable {
    private final SharedPreferences preferences;
    public static final String Key_Name="name";


     public Prefs(Context context) {
        preferences=context.getSharedPreferences("settings",Context.MODE_PRIVATE);

    }

    public void saveIsShown(){
        preferences.edit().putBoolean("isShown",true).apply();

    }
    public boolean isShown(){
        return preferences.getBoolean("isShown",false);
    }
    public void saveName(String name){
      preferences.edit().putString(Key_Name,name).apply();

    }
    public String name(){
        return  preferences.getString(Key_Name,null);
    }
     public  void sortName(boolean bool){
         preferences.edit().putBoolean("Sort",bool).apply();

     }
     public  boolean isSorted(){
         return  preferences.getBoolean("Sort",false);
     }

}

    


