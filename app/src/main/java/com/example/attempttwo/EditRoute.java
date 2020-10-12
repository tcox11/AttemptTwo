package com.example.attempttwo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
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

        routeNumber.setText("#" + String.valueOf(route.getId()));
        holdColour.setText("Hold colour: " + route.getHoldColour());
        routeArea.setText("Area: " + route.getArea());
        routeGrade.setText("Grade: " + route.getGrade());
        routeNotes.setText(route.getNotes());

        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_close);
        setTitle("Edit Route");
    }

    private void saveRoute(){
        String name = routeName.getText().toString();
        String description = routeDescription.getText().toString();
        String notes = routeDescription.getText().toString();

        Intent data = new Intent();
        data.putExtra(EXTRA_ROUTE, route);
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
            case R.id.save_route:
                saveRoute();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }
}
