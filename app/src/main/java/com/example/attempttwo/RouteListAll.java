package com.example.attempttwo;

import java.util.List;

import androidx.lifecycle.Observer;

public class RouteListAll extends RouteList {

    @Override
    protected void setAdapterRoutes(final RouteAdapter adapter) {

        routeViewModel.getAllRoutes().observe(this, new Observer<List<Route>>() {

            public void onChanged(List<Route> routes) {
                // update recyclerView
                adapter.setRoutes(routes);
            }
        });
    }
}