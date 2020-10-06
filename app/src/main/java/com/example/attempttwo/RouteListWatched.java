package com.example.attempttwo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.List;

public class RouteListWatched extends RouteList {

    @Override
    protected void setAdapterRoutes(final RouteAdapter adapter) {

        routeViewModel.getWatchedRoutes().observe(this, new Observer<List<Route>>() {
            public void onChanged(List<Route> routes) {
                // update recyclerView
                adapter.setRoutes(routes);
            }
        });
    }
}