package com.example.attempttwo;

import android.app.Application;
import android.util.Log;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

public class RouteViewModel extends AndroidViewModel {
    private RouteRepository repository;
    private LiveData<List<Route>> allRoutes;
    private LiveData<List<String>> allGrades;
    private LiveData<List<Route>> watchedRoutes;

    public RouteViewModel(@NonNull Application application) {
        super(application);
        repository = new RouteRepository(application);
        allRoutes = repository.getAllRoutes();
        allGrades = repository.getAllGrades();
        watchedRoutes = repository.getWatchedRoutes();
    }

    public void insert(Route route){
        repository.insert(route);
    }

    public void update(Route route){
        repository.update(route);
    }

    public LiveData<List<Route>> getAllRoutes(){
        return allRoutes;
    }

    public LiveData<List<Route>> getRoutesByGrade(String grade){
        return repository.getRoutesByGrade(grade);
    }

    public LiveData<List<Integer>>  getActiveRouteSums(){
        return repository.getActiveRouteSums();
    }

    public LiveData<List<Integer>> getCompletedRouteSums(){
        return repository.getCompletedRouteSums();
    }

    public LiveData<List<String>> getAllGrades(){ return allGrades; }

    public LiveData<List<Route>> getWatchedRoutes() {return watchedRoutes; };

}
