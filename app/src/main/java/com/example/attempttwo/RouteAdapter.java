package com.example.attempttwo;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;


public class RouteAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<Route> routes = new ArrayList<>();
    private OnWatchedClickListener watchedListener;
    private OnCompletedClickListener completedListener;
    private OnRouteClickListener routeListener;


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        if (viewType == 1) {
            View itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.route_header_item, parent, false);
            return new RouteHeaderHolder(itemView);
        } else {
            View itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.route_item, parent, false);
            return new RouteHolder(itemView);
        }
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder viewHolder, final int position) {
        int type = getItemViewType(position);
        Route currentRoute = routes.get(position);

        if (type == 1) {

            Boolean emptyArea = Boolean.FALSE;
            String whatwentwrong = "Nothing";
            RouteHeaderHolder holder = (RouteHeaderHolder) viewHolder;

            if (position == getItemCount() - 1) {
                whatwentwrong = "last position";
                // checks if last item is header, in which case removes from screen
                emptyArea = Boolean.TRUE;
            } else if (position < getItemCount() - 1 && routes.get(position + 1).getHeaderType() == 1) {
                //checks for two headers in a row
                whatwentwrong = "next to header";
                emptyArea = Boolean.TRUE;
            }

            if (!emptyArea) {
                holder.headerCardView.setVisibility(View.VISIBLE);
                holder.headerRelativeLayout.setVisibility(View.VISIBLE);
                holder.areaIcon.setVisibility(View.VISIBLE);
                holder.textViewHeader.setVisibility(View.VISIBLE);
                holder.areaIcon.setImageResource(currentRoute.getAreaIconID());
                holder.textViewHeader.setText(currentRoute.getArea());
                holder.areaIcon.getLayoutParams().height = (int) holder.areaIcon.getResources().getDimension(R.dimen.area_icon_height);
                holder.areaIcon.getLayoutParams().width = (int) holder.areaIcon.getResources().getDimension(R.dimen.area_icon_width);
            } else {
                holder.headerCardView.setVisibility(View.GONE);
                holder.headerRelativeLayout.setVisibility(View.GONE);
                holder.areaIcon.setVisibility(View.GONE);
                holder.textViewHeader.setVisibility(View.GONE);
            }

        } else {

            RouteHolder holder = (RouteHolder) viewHolder;
            holder.textViewRouteNumber.setText(currentRoute.getIDDisplay());
            //holder.textViewRouteName.setText(currentRoute.getNameDisplay());
            holder.frameLayout.setBackgroundResource(currentRoute.getColorID());
            holder.textViewRouteDescription.setText(currentRoute.getDescriptionDisplay());
            holder.holdIcon.setImageResource(currentRoute.getHoldIconID());

            //resize.. maybe rewrite this
            holder.holdIcon.getLayoutParams().height = (int) holder.holdIcon.getResources().getDimension(R.dimen.hold_icon_height);
            holder.holdIcon.getLayoutParams().width = (int) holder.holdIcon.getResources().getDimension(R.dimen.hold_icon_width);
            //setAreaIcon(holder, currentRoute);
            holder.checkBoxWatched.setChecked(currentRoute.getWatchlist() == 1);
            holder.checkBoxCompleted.setChecked(currentRoute.getCompleted() == 1);
        }


    }

    @Override
    public int getItemViewType(int position) {
        return routes.get(position).getHeaderType();
    }


    @Override
    public int getItemCount() {
        return routes.size();
    }

    public void setRoutes(List<Route> routes) {

        this.routes = routes;
        notifyDataSetChanged();
    }

    class RouteHeaderHolder extends RecyclerView.ViewHolder {
        private TextView textViewHeader;
        private CardView headerCardView;
        private ImageView areaIcon;
        private RelativeLayout headerRelativeLayout;

        public RouteHeaderHolder(View itemView) {
            super(itemView);
            textViewHeader = itemView.findViewById(R.id.route_header);
            headerCardView = itemView.findViewById(R.id.header_card_view);
            areaIcon = itemView.findViewById(R.id.area_icon);
            headerRelativeLayout = itemView.findViewById(R.id.header_relative_layout);
        }

    }

    class RouteHolder extends RecyclerView.ViewHolder {
        private TextView textViewRouteNumber;
        private TextView textViewRouteName;
        private TextView textViewRouteDescription;
        private CheckBox checkBoxWatched;
        private CheckBox checkBoxCompleted;
        private FrameLayout frameLayout;
        private ImageView holdIcon;
        private Button routeButton;


        public RouteHolder(View itemView) {
            super(itemView);
            routeButton = itemView.findViewById(R.id.route_item_button);
            textViewRouteNumber = itemView.findViewById(R.id.route_number);
            //textViewRouteName = itemView.findViewById(R.id.route_name);
            textViewRouteDescription = itemView.findViewById(R.id.route_description);
            checkBoxWatched = itemView.findViewById(R.id.watchedCheckBox);
            checkBoxCompleted = itemView.findViewById(R.id.completedCheckBox);
            holdIcon = itemView.findViewById(R.id.hold_icon);
            frameLayout = itemView.findViewById(R.id.card_frame_route);
            checkBoxWatched.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d("clicked", "clicked in adapater");
                    int position = getAdapterPosition();
                    Log.d("whereCalled", "watch button called at position" + String.valueOf(position));
                    if (watchedListener != null && position != RecyclerView.NO_POSITION) {
                        watchedListener.onItemClick(routes.get(position));
                    }
                }
            });

            checkBoxCompleted.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d("clicked", "clicked in adapater");
                    int position = getAdapterPosition();
                    if (completedListener != null && position != RecyclerView.NO_POSITION) {
                        completedListener.onItemClick(routes.get(position));
                    }
                }
            });

            routeButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (routeListener != null && position != RecyclerView.NO_POSITION) {
                        routeListener.onItemClick(routes.get(position));
                    }
                }
            });

        }
    }

    public interface OnWatchedClickListener {
        void onItemClick(Route route);

    }

    public void setOnItemWatchedClickListener(OnWatchedClickListener listener) {
        this.watchedListener = listener;
    }

    public interface OnCompletedClickListener {
        void onItemClick(Route route);

    }

    public void setOnItemCompletedClickListener(OnCompletedClickListener listener) {
        this.completedListener = listener;
    }

    public interface OnRouteClickListener {
        void onItemClick(Route route);
    }

    public void setOnItemRouteClickListener(OnRouteClickListener listener) {
        this.routeListener = listener;
    }

}
