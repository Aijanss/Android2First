package com.example.android2first.room;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.android2first.Note;

@Database(entities = {Note.class}, version = 1)

public abstract class AppData extends RoomDatabase {

    public abstract NoteDao noteDao();

}


