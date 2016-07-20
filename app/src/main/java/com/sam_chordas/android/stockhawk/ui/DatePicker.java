package com.sam_chordas.android.stockhawk.ui;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;

import java.util.Calendar;

/**
 * Created by pavan on 7/19/2016.
 */
public class DatePicker extends DialogFragment {



    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the current date as the default date in the picker
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        LineGraphActivity lineGraphActivity = new LineGraphActivity();

        DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(),lineGraphActivity,year,month,day);

        // Create a new instance of DatePickerDialog and return it
        return datePickerDialog ;
    }


}
