package com.example.attempttwo;

import android.content.Intent;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
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

        final String currentGrade = grades.get(position);
        setBorderFromString(holder, currentGrade);

        holder.buttonGrade.setText(currentGrade);
        // set stuff here
        holder.buttonGrade.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(v.getContext(), RouteListByGrade.class);
                intent.putExtra("grade", currentGrade);
                v.getContext().startActivity(intent);
            }

        });
    }

    private void openRoutesByGradeList(){
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
        private Button buttonGrade;
        private FrameLayout frameLayout;

        public GradeHolder(View itemView){
            super(itemView);
            buttonGrade = (Button) itemView.findViewById(R.id.button_each_grade);
            frameLayout = itemView.findViewById(R.id.card_frame_grade);
        }
    }
}
