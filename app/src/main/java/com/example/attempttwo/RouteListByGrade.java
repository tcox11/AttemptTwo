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

public class RouteListByGrade extends RouteList {

    private String grade;

    @Override
    protected void setAdapterRoutes(final RouteAdapter adapter) {

        Intent intent = getIntent();
        grade = intent.getExtras().getString("grade");
        routeViewModel.getRoutesByGrade(grade).observe(this, new Observer<List<Route>>() {
            public void onChanged(List<Route> routes) {
                // update recyclerView
                adapter.setRoutes(routes);
            }
        });
    }
}