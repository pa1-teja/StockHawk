package com.sam_chordas.android.stockhawk.ui;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;

import com.sam_chordas.android.stockhawk.R;

/**
 * Created by pavan on 7/18/2016.
 */
public class LineGraphActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {

    Button fromDate, toDate;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_line_graph);

        fromDate = (Button) findViewById(R.id.set_from_date);
        toDate   = (Button) findViewById(R.id.set_to_date);




        fromDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog(v);
            }
        });

        toDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog(v);
            }
        });

    }


    @Override
    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

    }

    public void showDatePickerDialog(View view){
        com.sam_chordas.android.stockhawk.ui.DatePicker datePicker = new com.sam_chordas.android.stockhawk.ui.DatePicker();

        datePicker.show(getSupportFragmentManager(),"datePicker");
    }

    public void populateSetDate(int year, int month, int day) {
        fromDate.setText(month+"/"+day+"/"+year);
    }

}
