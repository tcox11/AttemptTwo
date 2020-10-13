package com.example.attempttwo;

import android.content.Intent;
import android.graphics.Color;
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
    private String notes;
    private Integer active;
    private Integer watchlist;
    private Integer completed;
    private Integer headerType;
    private String dateSet;
    private String dateCompleted;
    private String setterDescription;
    private String climberDescription;
    private String setterRouteName;
    private String climberRouteName;
    private String holdType;
    private String xcoord;
    private String ycoord;


    public Route(String area, String holdColour, String grade, String notes, Integer active, Integer watchlist, Integer completed, Integer headerType, String dateSet, String dateCompleted, String setterDescription, String climberDescription, String setterRouteName, String climberRouteName, String holdType, String xcoord, String ycoord) {
        this.area = area;
        this.holdColour = holdColour;
        this.grade = grade;
        this.notes = notes;
        this.active = active;
        this.watchlist = watchlist;
        this.completed = completed;
        this.headerType = headerType;
        this.dateSet = dateSet;
        this.dateCompleted = dateCompleted;
        this.setterDescription = setterDescription;
        this.climberDescription = climberDescription;
        this.setterRouteName = setterRouteName;
        this.climberRouteName = climberRouteName;
        this.holdType = holdType;
        this.xcoord = xcoord;
        this.ycoord = ycoord;
    }

    public void setNotes(String notes) {
        this.notes = notes;
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


    public void setDateCompleted(String dateCompleted) {
        this.dateCompleted = dateCompleted;
    }

    public void setClimberDescription(String climberDescription) {
        this.climberDescription = climberDescription;
    }

    public void setClimberRouteName(String climberRouteName) {
        this.climberRouteName = climberRouteName;
    }

    public String getDateSet() {
        return dateSet;
    }

    public String getDateCompleted() {
        return dateCompleted;
    }

    public String getSetterDescription() {
        return setterDescription;
    }

    public String getClimberDescription() {
        return climberDescription;
    }

    public String getSetterRouteName() {
        return setterRouteName;
    }

    public String getClimberRouteName() {
        return climberRouteName;
    }

    public String getHoldType() {
        return holdType;
    }

    public String getXcoord() {
        return xcoord;
    }

    public String getYcoord() {
        return ycoord;
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
        dest.writeString(dateSet);
        dest.writeString(dateCompleted);
        dest.writeString(setterDescription);
        dest.writeString(climberDescription);
        dest.writeString(setterRouteName);
        dest.writeString(climberRouteName);
        dest.writeString(holdType);
        dest.writeString(xcoord);
        dest.writeString(ycoord);
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
        dateSet = in.readString();
        dateCompleted = in.readString();
        setterDescription = in.readString();
        climberDescription = in.readString();
        setterRouteName = in.readString();
        climberRouteName = in.readString();
        holdType = in.readString();
        xcoord = in.readString();
        ycoord = in.readString();

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
                + "Header type: " + String.valueOf(getHeaderType()) + "\n"
                + "need to change print file if you want more info! \n";
        Log.d(tag, toPrint);

    }

    public String getIDDisplay() {
        String idDisplay = "#" + String.valueOf(getId());
        return idDisplay;
    }

    public String getNameDisplay() {
        if (!climberRouteName.trim().isEmpty()) {
            return climberRouteName;
        } else if (!setterRouteName.trim().isEmpty()) {
            return setterRouteName;
        } else {
            String nameDisplay = "Route " + String.valueOf(getId());
            return nameDisplay;
        }
    }

    public String getDescriptionDisplay(){
        if(!climberDescription.trim().isEmpty()){
            return climberDescription;
        } else if (!setterDescription.trim().isEmpty()){
            return setterDescription;
        } else {
            Log.d("null", "returning null");
            return null;
        }

    }

    public int getColorID() {

        int colorID;
        String colorName;

        if (getGrade().length() > 2) {
            colorName = getGrade().substring(0, 2).toLowerCase() + "color";
            colorID = MyUtilites.getResId(colorName, R.color.class);
            if (colorID == -1) {
                colorID = R.color.cardColour;
            }
        } else {
            colorID = R.color.cardColour;
        }

        return colorID;
    }

    public int getHoldIconID() {
        int iconID = R.drawable.missing_hold;

        String colorName = getHoldColour().toLowerCase().replaceAll("/", "") + "_hold";
        int holdID = MyUtilites.getResId(colorName, R.drawable.class);
        if (holdID != -1) {
            iconID = holdID;
        }

        return iconID;

    }

    public int getAreaIconID() {
        int iconID = R.drawable.missing_area_icon;
        String areaName = getArea().toLowerCase().replaceAll(" ", "") + "_area";
        int areaID = MyUtilites.getResId(areaName, R.drawable.class);
        if (areaID != -1) {
            iconID = areaID;
        }
        return iconID;
    }
}
