package com.example.attempttwo;

import android.content.Intent;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

import java.io.Serializable;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "route_table")
public class Route implements Parcelable {

    @PrimaryKey(autoGenerate = true)
    private int id;

    private String area;
    private String holdColour;
    private String grade;

    public void setNotes(String notes) {
        this.notes = notes;
    }

    private String notes;
    private Integer active;
    private Integer watchlist;
    private Integer completed;
    private Integer headerType;


    public Route(String area, String holdColour, String grade, String notes, Integer active,
                 Integer watchlist, Integer completed, Integer headerType) {
        this.area = area;
        this.holdColour = holdColour;
        this.grade = grade;
        this.notes = notes;
        this.active = active;
        this.watchlist = watchlist;
        this.completed = completed;
        this.headerType = headerType;

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

    public Integer getHeaderType() {
        return headerType;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    public static final Parcelable.Creator<Route> CREATOR = new Parcelable.Creator<Route>() {
        public Route createFromParcel(Parcel in) {
            return new Route(in);
        }

        public Route[] newArray(int size) {
            return new Route[size];
        }

    };

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(area);
        dest.writeString(holdColour);
        dest.writeString(grade);
        dest.writeString(notes);
        dest.writeInt(active);
        dest.writeInt(watchlist);
        dest.writeInt(completed);
        dest.writeInt(headerType);
    }

    private Route(Parcel in) {
        id = in.readInt();
        area = in.readString();
        holdColour = in.readString();
        grade = in.readString();
        notes = in.readString();
        active = in.readInt();
        watchlist = in.readInt();
        completed = in.readInt();
        headerType = in.readInt();
    }

    public void printRouteLog(String tag) {
        String toPrint;
        toPrint = "Route ID: " + String.valueOf(getId()) + "\n"
                + "Area: " + getArea() + "\n"
                + "Hold Colour: " + getHoldColour() + "\n"
                + "Grade: " + getGrade() + "\n"
                + "Notes: " + getNotes() + "\n"
                + "Active: " + String.valueOf(getActive()) + "\n"
                + "Watchlist: " + String.valueOf(getWatchlist()) + "\n"
                + "Completed: " + String.valueOf(getCompleted()) + "\n"
                + "Header type: " + String.valueOf(getHeaderType()) + "\n";
        Log.d(tag, toPrint);

    }
}
