package com.example.attempttwo;

import android.app.Application;
import android.os.AsyncTask;

import java.util.List;

import androidx.lifecycle.LiveData;

public class RouteRepository {

    private RouteDao routeDao;
    private LiveData<List<Route>> allRoutes;
    private LiveData<List<String>> allGrades;
    private LiveData<List<Route>> watchedRoutes;

    public RouteRepository(Application application){
        RouteDatabase database = RouteDatabase.getInstance(application);
        routeDao = database.routeDao();
        allRoutes = routeDao.getAllRoutes();
        allGrades = routeDao.getAllGrades();
        watchedRoutes = routeDao.getWatchedRoutes();

    }

    public void update(Route route){
        new UpdateRouteAsyncTask(routeDao).execute(route);
    }

    public void insert(Route route){
        new InsertRouteAsyncTask(routeDao).execute(route);
    }

    public LiveData<List<Route>> getAllRoutes(){
        return allRoutes;
    }

    public LiveData<List<Route>> getRoutesByGrade(String grade){
        return routeDao.getRoutesByGrade(grade);
    }

    public LiveData<List<Route>> getWatchedRoutes(){ return watchedRoutes; };

    public LiveData<List<String>> getAllGrades(){
        return allGrades;
    }

    private static class UpdateRouteAsyncTask extends AsyncTask<Route, Void, Void>{
        private RouteDao routeDao;

        private UpdateRouteAsyncTask(RouteDao routeDao){
            this.routeDao = routeDao;
        }

        @Override
        protected Void doInBackground(Route... routes) {
            routeDao.update(routes[0]);
            return null;
        }
    }

    private static class InsertRouteAsyncTask extends AsyncTask<Route, Void, Void>{
        private RouteDao routeDao;

        private InsertRouteAsyncTask(RouteDao routeDao){
            this.routeDao = routeDao;
        }

        @Override
        protected Void doInBackground(Route... routes) {
            routeDao.insert(routes[0]);
            return null;
        }
    }

}
