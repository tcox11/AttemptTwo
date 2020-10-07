package com.example.attempttwo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class RoutesByGrade extends AppCompatActivity {


    private RouteViewModel routeViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_routes_by_grade);

        RecyclerView recyclerView = findViewById(R.id.grade_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        final GradeAdapter adapter = new GradeAdapter();
        recyclerView.setAdapter(adapter);

        routeViewModel = new ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory.getInstance(this.getApplication())).get(RouteViewModel.class);
        routeViewModel.getAllGrades().observe(this, new Observer<List<String>>() {
            @Override
            public void onChanged(List<String> grades) {
                // update recyclerView
                adapter.setGrades(grades);
            }
        });

        routeViewModel.getActiveRouteSums().observe(this, new Observer<List<Integer>>() {
            @Override
            public void onChanged(List<Integer> activeTotals) {
                adapter.setTotalRoutes(activeTotals);
                Log.d("activeTotals", "length of active totals is " + String.valueOf(activeTotals.size()));

            }
        });

        routeViewModel.getCompletedRouteSums().observe(this, new Observer<List<Integer>>() {
            @Override
            public void onChanged(List<Integer> completedTotals) {
                adapter.setCompleted(completedTotals);
                Log.d("activeTotals", "length of completed totals is " + String.valueOf(completedTotals.size()));
            }
        });



    }
}
