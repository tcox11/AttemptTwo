package com.example.attempttwo;

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
import androidx.recyclerview.widget.RecyclerView;

public class GradeAdapter extends RecyclerView.Adapter <GradeAdapter.GradeHolder>{

    private List<Route> routes = new ArrayList<>();
    private List<String> grades = new ArrayList<>();

    @NonNull
    @Override
    public GradeHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.grade_item, parent, false);
        return new GradeHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull GradeAdapter.GradeHolder holder, int position) {

        holder.textViewGradeName .setText(grades.get(position));
        // set stuff here
    }

    public int getItemCount() {
        //very hacky, needs to return total number of grades
        return grades.size();
    }

    public void setRoutes(List<Route> routes){
        this.routes = routes;
        notifyDataSetChanged();
    }

    public void setGrades(List<String> grades){
        this.grades = grades;
        notifyDataSetChanged();
    }

    class GradeHolder extends RecyclerView.ViewHolder{
        private TextView textViewGradeName;

        public GradeHolder(View itemView){
            super(itemView);
            textViewGradeName = itemView.findViewById(R.id.text_view_grade_name);
        }
    }
}
