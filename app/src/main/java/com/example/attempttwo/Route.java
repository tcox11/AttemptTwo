package com.example.attempttwo;

import android.content.Intent;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "route_table")
public class Route {

    @PrimaryKey(autoGenerate = true)
    private int id;

    private String area;
    private String holdColour;
    private String grade;
    private String notes;
    private Integer active;
    private Integer watchlist;
    private Integer completed;


    public Route(String area, String holdColour, String grade, String notes, Integer active,
                 Integer watchlist, Integer completed) {
        this.area = area;
        this.holdColour = holdColour;
        this.grade = grade;
        this.notes = notes;
        this.active = active;
        this.watchlist = watchlist;
        this.completed = completed;
    }


    public void setWatchlist(Integer watchlist) {
        this.watchlist = watchlist;
    }

    public void setCompleted(Integer completed) {
        this.completed = completed;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getArea() {
        return area;
    }

    public String getHoldColour() {
        return holdColour;
    }

    public String getGrade() {
        return grade;
    }

    public String getNotes() {
        return notes;
    }

    public Integer getActive() {
        return active;
    }

    public Integer getWatchlist() {
        return watchlist;
    }

    public Integer getCompleted() {
        return completed;
    }

}
