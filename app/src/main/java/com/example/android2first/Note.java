package com.example.android2first;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;
@Entity
public class Note implements Serializable {
    @PrimaryKey (autoGenerate = true)
    private long id;
    private String title;
    private String data;

    public Note(String title ,String data) {
        this.title = title;
        this.data = data;
    }

    public Note() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle( String title) {
        this.title = title;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}

