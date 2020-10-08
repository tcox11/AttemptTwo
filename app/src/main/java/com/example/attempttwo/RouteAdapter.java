package com.example.attempttwo;

import android.app.Activity;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
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
    private OnWatchedClickListener watchedListener;
    private OnCompletedClickListener completedListener;


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
        setName(holder, currentRoute);
        setBorder(holder, currentRoute);
        setHoldIcon(holder, currentRoute);
        setAreaIcon(holder, currentRoute);
        holder.checkBoxWatched.setChecked(currentRoute.getWatchlist() == 1);
        holder.checkBoxCompleted.setChecked(currentRoute.getCompleted() == 1);

    }



    private void setName(RouteHolder holder, Route route){
        String routeName = "Route #" + String.valueOf(route.getId());
        holder.textViewRouteName.setText(routeName);

    }

    private void setBorder(RouteHolder holder, Route route){
        String colorName;

        if(route.getGrade().length() > 2) {
            colorName = route.getGrade().substring(0, 2).toLowerCase() + "color";
            int colorID = MyUtilites.getResId(colorName, R.color.class);
            if (colorID == -1){
                Log.d("hello", "missing colour");
                holder.frameLayout.setBackgroundColor(Color.parseColor("#FFFFFF"));
            } else {

                Log.d("hello", "colour set correctly: " + colorName);
                holder.frameLayout.setBackgroundResource(colorID);
            }
        }else{
            Log.d("hello", "gradeLength too short");
            holder.frameLayout.setBackgroundColor(Color.parseColor("#FFFFFF"));
        }


    }

    private void setHoldIcon(RouteHolder holder, Route route){
        String colorName = route.getHoldColour().toLowerCase().replaceAll("/", "") + "_hold";
        int holdID = MyUtilites.getResId(colorName, R.drawable.class);
        if(holdID == -1){
            Log.d("hello", "missing hold icon");
            holder.holdIcon.setImageResource(R.drawable.missing_hold);
        } else {
            holder.holdIcon.setImageResource(holdID);
        }

        holder.holdIcon.getLayoutParams().height = (int) holder.holdIcon.getResources().getDimension(R.dimen.hold_icon_height);
        holder.holdIcon.getLayoutParams().width = (int) holder.holdIcon.getResources().getDimension(R.dimen.hold_icon_width);

    }

    private void setAreaIcon(RouteHolder holder, Route route){
        String areaName = route.getArea().toLowerCase().replaceAll(" ", "") + "_area";
        Log.d("area", areaName);
        int areaID = MyUtilites.getResId(areaName, R.drawable.class);
        if(areaID == -1){
            Log.d("hello", "missing hold icon");
            //leave blank
        } else {
            holder.areaIcon.setImageResource(areaID);
        }

        holder.areaIcon.getLayoutParams().height = (int) holder.areaIcon.getResources().getDimension(R.dimen.area_icon_height);
        holder.areaIcon.getLayoutParams().width = (int) holder.areaIcon.getResources().getDimension(R.dimen.area_icon_width);

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
        private ImageView areaIcon;

        public RouteHolder(View itemView){
            super(itemView);
            textViewRouteName = itemView.findViewById(R.id.text_view_route_name);
            checkBoxWatched = itemView.findViewById(R.id.watchedCheckBox);
            checkBoxCompleted = itemView.findViewById(R.id.completedCheckBox);
            frameLayout = itemView.findViewById(R.id.card_frame_route);
            holdIcon = itemView.findViewById(R.id.hold_icon);
            areaIcon = itemView.findViewById(R.id.area_icon);
            checkBoxWatched.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d("clicked", "clicked in adapater");
                    int position = getAdapterPosition();
                    if(watchedListener != null && position != RecyclerView.NO_POSITION){
                        watchedListener.onItemClick(routes.get(position));
                    }
                }
            });

            checkBoxCompleted.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d("clicked", "clicked in adapater");
                    int position = getAdapterPosition();
                    if(completedListener != null && position != RecyclerView.NO_POSITION){
                        completedListener.onItemClick(routes.get(position));
                    }
                }
            });

        }
    }

    public interface OnWatchedClickListener  {
        void onItemClick(Route route);

    }
    public void setOnItemWatchedClickListener(OnWatchedClickListener listener){
        this.watchedListener = listener;
    }

    public interface OnCompletedClickListener  {
        void onItemClick(Route route);

    }
    public void setOnItemCompletedClickListener(OnCompletedClickListener listener){
        this.completedListener = listener;
    }

}
