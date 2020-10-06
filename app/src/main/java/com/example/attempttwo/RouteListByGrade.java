package com.example.attempttwo;

import android.content.Intent;
import android.os.Bundle;

import com.example.attempttwo.R;
import com.example.attempttwo.Route;
import com.example.attempttwo.RouteAdapter;
import com.example.attempttwo.RouteViewModel;

import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class RouteListByGrade extends AppCompatActivity {
    private RouteViewModel routeViewModel;
    private String grade;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_route_list_all);

        Intent intent = getIntent();
        grade = intent.getExtras().getString("grade");

        RecyclerView recyclerView = findViewById(R.id.route_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        final RouteAdapter adapter = new RouteAdapter();
        recyclerView.setAdapter(adapter);

        routeViewModel = new ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory.getInstance(this.getApplication())).get(RouteViewModel.class);
        routeViewModel.getRoutesByGrade(grade).observe(this, new Observer<List<Route>>() {
            @Override
            public void onChanged(List<Route> routes) {
                // update recyclerView
                adapter.setRoutes(routes);
            }
        });
        
    }
}
