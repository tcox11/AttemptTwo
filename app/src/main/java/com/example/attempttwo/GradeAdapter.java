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

        String currentGrade = grades.get(position);
        holder.textViewGradeName.setText(currentGrade);
        setBorderFromString(holder, currentGrade);
        // set stuff here
    }


    private void setBorderFromString(GradeAdapter.GradeHolder holder, String grade){
        String colorName = grade.substring(0,2).toLowerCase() + "color";
        int colorID = MyUtilites.getResId(colorName, R.color.class);
        if (colorID == -1){
            Log.d("hello", "missing colour");
            holder.frameLayout.setBackgroundColor(Color.parseColor("#FFFFFF"));
        } else {
            holder.frameLayout.setBackgroundResource(colorID);
        }

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
        private FrameLayout frameLayout;

        public GradeHolder(View itemView){
            super(itemView);
            textViewGradeName = itemView.findViewById(R.id.text_view_grade_name);
            frameLayout = itemView.findViewById(R.id.card_frame_grade);
        }
    }
}
