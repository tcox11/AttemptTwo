package com.example.attempttwo;

import android.app.Activity;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
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
        Route currentRoute = routes.get(position);
        String currentRouteName = currentRoute.getArea() + ", "
                                  + currentRoute.getHoldColour().toLowerCase() + " holds, "
                                  + currentRoute.getGrade();
        String colorName = currentRoute.getGrade().substring(0,2).toLowerCase() + "color";
        int colorID = MyUtilites.getResId(colorName, R.color.class);
        if (colorID == -1){
            Log.d("hello", "missing colour");
            holder.frameLayout.setBackgroundColor(Color.parseColor("#FFFFFF"));
        } else {
            holder.frameLayout.setBackgroundResource(colorID);
            //holder.holdIcon.setImageResource(R.drawable.completed_button_clicked);
        }
        holder.textViewRouteName.setText(currentRouteName);
        holder.checkBoxWatched.setChecked(currentRoute.getWatchlist()==1);
        holder.checkBoxCompleted.setChecked(currentRoute.getCompleted()==1);

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
        private FrameLayout frameLayout;
        private ImageView holdIcon;

        public RouteHolder(View itemView){
            super(itemView);
            textViewRouteName = itemView.findViewById(R.id.text_view_route_name);
            checkBoxWatched = itemView.findViewById(R.id.watchedCheckBox);
            checkBoxCompleted = itemView.findViewById(R.id.completedCheckBox);
            frameLayout = itemView.findViewById(R.id.card_frame);
            holdIcon = itemView.findViewById(R.id.hold_icon);
        }
    }
}
