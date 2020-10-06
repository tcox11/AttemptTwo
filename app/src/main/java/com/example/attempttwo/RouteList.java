package com.example.attempttwo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import java.util.List;

public abstract class RouteList extends AppCompatActivity {

    protected RouteViewModel routeViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_route_list);

        RecyclerView recyclerView = findViewById(R.id.route_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        final RouteAdapter adapter = new RouteAdapter();
        recyclerView.setAdapter(adapter);
        routeViewModel = new ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory.getInstance(this.getApplication())).get(RouteViewModel.class);
        setAdapterRoutes(adapter);

        adapter.setOnItemWatchedClickListener(new RouteAdapter.OnWatchedClickListener() {
            @Override
            public void onItemClick(Route route) {

                Log.d("clicked", "clicked in list");
                route.setWatchlist(1-route.getWatchlist());
                routeViewModel.update(route);
            }
        });

        adapter.setOnItemCompletedClickListener(new RouteAdapter.OnCompletedClickListener() {
            @Override
            public void onItemClick(Route route) {

                Log.d("clicked", "clicked in list");
                route.setCompleted(1-route.getCompleted());
                routeViewModel.update(route);
            }
        });




    }

    protected abstract void setAdapterRoutes(final RouteAdapter adapter);
}

