package com.example.attempttwo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import java.util.List;

// Made a new xml file for main activity. Don't know if it needs anythin added as it is the main xml
public class MainActivity extends AppCompatActivity {

    private RouteViewModel routeViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(R.id.route_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        final RouteAdapter adapter = new RouteAdapter();
        recyclerView.setAdapter(adapter);

        routeViewModel = new ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory.getInstance(this.getApplication())).get(RouteViewModel.class);
        routeViewModel.getAllRoutes().observe(this, new Observer<List<Route>>() {
            @Override
            public void onChanged(List<Route> routes) {
                // update recyclerView
                adapter.setRoutes(routes);
            }
        });

    }


}
