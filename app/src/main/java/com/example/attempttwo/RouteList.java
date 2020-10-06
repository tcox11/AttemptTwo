package com.example.attempttwo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

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

    }

    protected abstract void setAdapterRoutes(final RouteAdapter adapter);
}

