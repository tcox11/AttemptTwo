package com.example.attempttwo;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public interface RouteDao {

    @Update
    void update(Route route);

    @Insert
    void insert(Route route);

    @Query("SELECT * FROM route_table ORDER BY grade ASC")
    LiveData<List<Route>> getAllRoutes();

    // probably doesn't need to be live, except doing it the other needs async tasks which I dont
    // bleddy understand
    @Query("SELECT DISTINCT grade FROM route_table ORDER BY grade ASC")
    LiveData<List<String>> getAllGrades();

}
