package com.example.attempttwo;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import java.util.List;

public abstract class RouteList extends AppCompatActivity {

    public static final int EDIT_ROUTE_REQUEST = 1;

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

        adapter.setOnItemRouteClickListener(new RouteAdapter.OnRouteClickListener() {
            @Override
            public void onItemClick(Route route) {
                Intent intent = new Intent(RouteList.this, EditRoute.class);
                intent.putExtra(EditRoute.EXTRA_ROUTE, route);
                startActivityForResult(intent, EDIT_ROUTE_REQUEST);
            }
        });




    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==EDIT_ROUTE_REQUEST && resultCode==RESULT_OK){
            Route returnedRoute = data.getParcelableExtra(EditRoute.EXTRA_ROUTE);

            // do stuff with route here
            if(returnedRoute!=null) {
                routeViewModel.update(returnedRoute);
            } else{
                CharSequence text = "Edit route not saved";
                int duration = Toast.LENGTH_SHORT;
                Toast toast = Toast.makeText(RouteList.this, text, duration);
                toast.show();
            }


        }
    }

    protected abstract void setAdapterRoutes(final RouteAdapter adapter);
}

