package com.example.attempttwo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.Serializable;

public class EditRoute extends AppCompatActivity {

    public static final String EXTRA_ROUTE = "com.example.attempttwo.EXTRA_ROUTE";


    private Route route;
    private TextView routeNumber;
    private EditText routeName;
    private TextView holdColour;
    private TextView routeArea;
    private TextView routeGrade;
    private EditText routeDescription;
    private EditText routeNotes;
    private FrameLayout backingFrame;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_route);


        route = getIntent().getParcelableExtra(EXTRA_ROUTE);

        routeNumber = findViewById(R.id.edit_route_number);
        routeName = findViewById(R.id.edit_route_name);
        holdColour = findViewById(R.id.edit_route_hold_colour);
        routeGrade = findViewById(R.id.edit_route_grade);
        routeArea = findViewById(R.id.edit_route_area);
        routeDescription = findViewById(R.id.edit_route_description);
        routeNotes = findViewById(R.id.edit_route_notes);
        backingFrame = findViewById(R.id.edit_route_back_frame);

        backingFrame.setBackgroundResource(route.getColorID());
        routeNumber.setText(route.getIDDisplay());
        routeName.setText(route.getNameDisplay());
        routeDescription.setText(route.getDescriptionDisplay());

        routeArea.setText(route.getArea());
        holdColour.setText(route.getHoldColour() + " holds");
        routeGrade.setText(route.getGrade());
        routeNotes.setText(route.getNotes());

        setTitle("Edit Route");
    }

    private void saveRoute(){
        String name = routeName.getText().toString();
        String description = routeDescription.getText().toString();
        String notes = routeNotes.getText().toString();

        Route newRoute = route;

        newRoute.setNotes(notes);
        newRoute.setClimberRouteName(name);
        newRoute.setClimberDescription(description);
        Intent data = new Intent();
        data.putExtra(EXTRA_ROUTE, newRoute);
        setResult(RESULT_OK, data);
        finish();


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.edit_route_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
            case R.id.save_route:
                saveRoute();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }
}
