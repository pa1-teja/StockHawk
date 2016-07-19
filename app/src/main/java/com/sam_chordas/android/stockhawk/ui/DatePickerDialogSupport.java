package com.sam_chordas.android.stockhawk.ui;

import android.app.DatePickerDialog;
import android.content.Context;
import android.util.Log;
import android.widget.DatePicker;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by pavan on 7/19/2016.
 */
public class DatePickerDialogSupport implements DatePickerDialog.OnDateSetListener {

    Context context;

    public DatePickerDialogSupport(Context context) {
        this.context = context;
    }

    @Override
    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
        // Do something with the date chosen by the user

        Log.d("qwerty", "kirkiri : " + year + " " + monthOfYear + " " + dayOfMonth);

        Calendar c = Calendar.getInstance();
        c.set(year, monthOfYear, dayOfMonth);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");


    }
}
