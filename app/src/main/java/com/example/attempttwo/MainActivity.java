package com.example.attempttwo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

// Made a new xml file for main activity. Don't know if it needs anythin added as it is the main xml
public class MainActivity extends AppCompatActivity {

    private Button goToViewAllRoutes;
    private Button goToRoutesByGrade;
    private Button goToWatchedRoutes;
    private TextView watchedMessage;
    private RouteViewModel routeViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        goToViewAllRoutes = (Button) findViewById(R.id.goToAllRoutes);
        goToViewAllRoutes.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                openViewAllRoutes();
            }

        });

        goToRoutesByGrade = (Button) findViewById(R.id.goToRoutesByGrade);
        goToRoutesByGrade.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                openRoutesByGrade();
            }

        });


        goToWatchedRoutes = (Button) findViewById(R.id.goToWatchedRoutes);
        goToWatchedRoutes.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                openWatchedRoutes();
            }

        });

        watchedMessage = (TextView) findViewById(R.id.mm_watchlist_message);
        routeViewModel = new ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory.getInstance(this.getApplication())).get(RouteViewModel.class);

        routeViewModel.getWatchedUncompletedSum().observe(this, new Observer<Integer>() {
            public void onChanged(Integer watchedRouteSum) {
                // update recyclerView
                watchedMessage.setText("(Incomplete routes: " + String.valueOf(watchedRouteSum) + ")");
            }
        });

    }

    private void openViewAllRoutes() {
        Intent intent = new Intent(this, RouteListAll.class);
        startActivity(intent);
    }

    private void openRoutesByGrade(){
        Intent intent = new Intent(this, RoutesByGrade.class);
        startActivity(intent);
    }

    private void openWatchedRoutes(){
        Intent intent = new Intent(this, RouteListWatched.class);
        startActivity(intent);
    }


}
