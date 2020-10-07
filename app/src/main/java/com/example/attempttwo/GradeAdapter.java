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
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class GradeAdapter extends RecyclerView.Adapter <GradeAdapter.GradeHolder>{

    private List<String> grades = new ArrayList<>();
    private List<Integer> completedRoutes = new ArrayList<>();
    private List<Integer> totalRoutes = new ArrayList<>();

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

        String progressMessage;
        Integer progressValue;
        //HOPEFULLY THIS CAN BE TIDIED UP FIGURED OUT HOW TO RETURN OBJECT FROM DAO QUERY
        if(grades.size() == completedRoutes.size() && grades.size() == totalRoutes.size()){
            progressMessage = "Progress: " + String.valueOf(completedRoutes.get(position))
                              + "/" + String.valueOf(totalRoutes.get(position));
            progressValue = completedRoutes.get(position) * 100 / totalRoutes.get(position);
        } else {
            progressMessage = "Unable to show progress.";
            progressValue = 0;
        }
        holder.progressText.setText(progressMessage);
        holder.progressBar.setProgress(progressValue);




        holder.buttonGrade.setText(currentGrade);
        holder.buttonGrade.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(v.getContext(), RouteListByGrade.class);
                intent.putExtra("grade", currentGrade);
                v.getContext().startActivity(intent);
            }

        });
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


    public void setGrades(List<String> grades){
        //IF THE LENGTH OF THESE THREE FUNCTION INPUTS VARIES, SOMETHING HAS GONE WRONG
        //HOPEFULLY THIS CAN BE TIDIED UP FIGURED OUT HOW TO RETURN OBJECT FROM DAO QUERY
        this.grades = grades;
        notifyDataSetChanged();
    }

    public void setCompleted(List<Integer> completedRoutes){
        //IF THE LENGTH OF THESE THREE FUNCTION INPUTS VARIES, SOMETHING HAS GONE WRONG
        this.completedRoutes = completedRoutes;
        notifyDataSetChanged();
    }


    public void setTotalRoutes(List<Integer> totalRoutes){
        //IF THE LENGTH OF THESE THREE FUNCTION INPUTS VARIES, SOMETHING HAS GONE WRONG
        this.totalRoutes = totalRoutes;
        notifyDataSetChanged();
    }

    class GradeHolder extends RecyclerView.ViewHolder{
        private Button buttonGrade;
        private FrameLayout frameLayout;
        private TextView progressText;
        private ProgressBar progressBar;

        public GradeHolder(View itemView){
            super(itemView);
            buttonGrade = (Button) itemView.findViewById(R.id.button_each_grade);
            frameLayout = itemView.findViewById(R.id.card_frame_grade);
            progressText = itemView.findViewById(R.id.grade_progress_text);
            progressBar = itemView.findViewById(R.id.grade_progress_bar);
        }
    }
}
