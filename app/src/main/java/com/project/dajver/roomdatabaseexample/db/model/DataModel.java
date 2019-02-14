package com.project.dajver.roomdatabaseexample.db.model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.graphics.Color;
import android.support.annotation.NonNull;

import java.util.Date;

/**
 * Created by gleb on 11/15/17.
 */

@Entity
public class DataModel {

    @NonNull
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String title;
    private String description;
    private String date;
    private String time;
    private String priority;
    private int color;

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    @NonNull
    public String getTitle() {
        return title;
    }

    public void setTitle(@NonNull String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
