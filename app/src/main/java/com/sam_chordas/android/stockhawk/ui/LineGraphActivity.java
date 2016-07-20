package com.sam_chordas.android.stockhawk.ui;

import android.app.DatePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.sam_chordas.android.stockhawk.R;

/**
 * Created by pavan on 7/18/2016.
 */
public class LineGraphActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {

    Button fromDate, toDate;

    static TextView chosenFromDate, chosenToDate;

    static Context context;
    static boolean fD;
    static boolean tD;
    private String inputFromDate = null;
    private String inputTodate = null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_line_graph);

        fromDate = (Button) findViewById(R.id.set_from_date);
        toDate   = (Button) findViewById(R.id.set_to_date);

        chosenFromDate = (TextView) findViewById(R.id.chosen_from_date);
        chosenToDate   = (TextView) findViewById(R.id.chosen_to_date);

        context = getApplicationContext();


        fromDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog(v);
                fD = true;
            }
        });

        toDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog(v);
                tD = true;
            }
        });

    }



    public void showDatePickerDialog(View view){
        com.sam_chordas.android.stockhawk.ui.DatePicker datePicker = new com.sam_chordas.android.stockhawk.ui.DatePicker();

        datePicker.show(getFragmentManager(),"datePicker");
    }

    @Override
    public void onDateSet(android.widget.DatePicker view, int year, int monthOfYear, int dayOfMonth) {
        // Do something with the date chosen by the user

        if (fD){
            inputFromDate = year + "/" + monthOfYear + "/" + dayOfMonth;
            Log.d("inputFromDate",inputFromDate);
            chosenFromDate.setText(context.getString(R.string.from_date) + " " + inputFromDate);
            fD =false;
        }else if (tD){
            inputTodate = year + "/" + monthOfYear + "/" + dayOfMonth;
            Log.d("inputTodate",inputTodate);
            chosenToDate.setText(context.getString(R.string.to_date) + " " +inputTodate);
            tD = false;
        }


    }
}
