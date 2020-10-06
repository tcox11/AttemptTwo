package com.example.attempttwo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import java.util.List;

public class RouteListAll extends AppCompatActivity {

    private RouteViewModel routeViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_route_list_all);

        RecyclerView recyclerView = findViewById(R.id.route_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        final RouteAdapter adapter = new RouteAdapter();
        recyclerView.setAdapter(adapter);
        routeViewModel = new ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory.getInstance(this.getApplication())).get(RouteViewModel.class);
        setAdapterRoutes(adapter);

    }

    private void setAdapterRoutes(final RouteAdapter adapter){

        routeViewModel.getAllRoutes().observe(this, new Observer<List<Route>>() {
            @Override
            public void onChanged(List<Route> routes) {
                // update recyclerView
                adapter.setRoutes(routes);
            }
        });

    }


}
