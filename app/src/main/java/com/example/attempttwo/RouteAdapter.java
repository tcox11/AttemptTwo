package com.example.attempttwo;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


public class RouteAdapter extends RecyclerView.Adapter<RouteAdapter.RouteHolder> {
    private List<Route> routes = new ArrayList<>();

    @NonNull
    @Override
    public RouteHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.route_item, parent, false);
        return new RouteHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull RouteHolder holder, int position) {
        Log.d("hello", String.valueOf(position));
        Route currentRoute = routes.get(position);
        // MORE ELEGANT SOLUTION FOR NAMING ROUTES
        String currentRouteName = currentRoute.getHoldColour() + " " + currentRoute.getGrade();
        holder.textViewRouteName.setText(currentRouteName);
        // SETTING CHECKBOXES

    }

    @Override
    public int getItemCount() {
        return routes.size();
    }

    public void setRoutes(List<Route> routes){
        this.routes = routes;
        notifyDataSetChanged();
    }

    class RouteHolder extends RecyclerView.ViewHolder{
        private TextView textViewRouteName;
        private CheckBox checkBoxWatched;
        private CheckBox checkBoxCompleted;

        public RouteHolder(View itemView){
            super(itemView);
            textViewRouteName = itemView.findViewById(R.id.text_view_route_name);
            checkBoxWatched = itemView.findViewById(R.id.watchedCheckBox);
            checkBoxCompleted = itemView.findViewById(R.id.completedCheckBox);
        }
    }
}
