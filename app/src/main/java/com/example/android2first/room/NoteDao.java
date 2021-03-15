package com.example.android2first.room;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Database;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.android2first.Note;

import java.util.List;

@Dao
public interface NoteDao {

    @Query("SELECT * FROM note")
    List<Note> getAll();

    @Query("SELECT * FROM note")
    LiveData<List<Note>>  getAllLive();

    @Insert
    void insert(Note note);

    @Delete
    void delete(Note note);

    @Update (onConflict = OnConflictStrategy.REPLACE)
    void update(Note note);

     @Query("SELECT *FROM note ORDER BY title ASC")
      List<Note> getSortedList();

}
