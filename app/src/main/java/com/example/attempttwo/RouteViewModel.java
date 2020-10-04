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

    public RouteViewModel(@NonNull Application application) {
        super(application);
        repository = new RouteRepository(application);
        allRoutes = repository.getAllRoutes();
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
}
