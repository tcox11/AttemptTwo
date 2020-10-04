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

}
