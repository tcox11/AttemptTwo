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

    @Query("SELECT * FROM route_table WHERE active = 1 OR headerType = 1 ORDER BY area ASC, headerType DESC, id ASC")
    LiveData<List<Route>> getAllRoutes();

    @Query("SELECT * FROM route_table WHERE grade = :grade AND active = 1 OR headerType = 1 ORDER BY area ASC, headerType DESC, id ASC")
    LiveData<List<Route>> getRoutesByGrade(String grade);

    @Query("SELECT * FROM route_table WHERE active = 1 AND watchlist = 1 ORDER BY grade ASC")
    LiveData<List<Route>> getWatchedRoutes();


    @Query("SELECT SUM(watchlist) FROM route_table  WHERE active = 1 AND completed = 0")
    LiveData<Integer> getWatchedUncompletedSum();

    // probably doesn't need to be live, except doing it the other needs async tasks which I dont
    // bleddy understand
    // CAN HOPEFULLY GROUP ALL THESE 3 QUERIES INTO ONE QUERY BY CREATING A GRADE CLASS

    @Query("SELECT DISTINCT grade FROM route_table WHERE active = 1  ORDER BY grade ASC")
    LiveData<List<String>> getAllGrades();

    @Query("SELECT SUM(active) FROM route_table WHERE active = 1  GROUP BY grade ORDER BY grade ASC")
    LiveData<List<Integer>> getActiveRouteSums();

    @Query("SELECT SUM(completed) FROM route_table WHERE active = 1 GROUP BY grade ORDER BY grade ASC")
    LiveData<List<Integer>> getCompletedRouteSums();

}
