package com.sam_chordas.android.stockhawk.ui;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TableLayout;
import android.widget.TextView;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.sam_chordas.android.stockhawk.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by pavan on 7/18/2016.
 */
public class LineGraphActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {

    Button fromDate, toDate;
    static ProgressBar progressBar;

    public static LineChart mPlotGraph;

    static TextView chosenFromDate, chosenToDate;

    static TextView dates_check;

    static TableLayout buttonPanel;


    static String chosenSymbol;
    static Context context;
    static boolean fD;
    static boolean tD;
    private static String inputStartDate = null;
    private static String inputEndDate = null;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_line_graph);

        Intent intent = getIntent();

        mPlotGraph = (LineChart) findViewById(R.id.lineChart);

        chosenSymbol = intent.getStringExtra(Intent.EXTRA_TEXT);

        Log.d(getClass().getSimpleName(), "symbl : " + chosenSymbol);

        fromDate = (Button) findViewById(R.id.set_from_date);
        toDate = (Button) findViewById(R.id.set_to_date);


        buttonPanel = (TableLayout) findViewById(R.id.buttonPanel);

        progressBar = (ProgressBar) findViewById(R.id.history_progress);
        chosenFromDate = (TextView) findViewById(R.id.chosen_from_date);
        chosenToDate = (TextView) findViewById(R.id.chosen_to_date);
        dates_check = (TextView) findViewById(R.id.dates_check);

        inputStartDate = null;
        inputEndDate = null;

        context = getApplicationContext();

        dates_check.setVisibility(View.GONE);


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

        if (inputStartDate == null && inputEndDate == null)
            dates_check.setVisibility(View.VISIBLE);

    }

    public void showDatePickerDialog(View view) {
        com.sam_chordas.android.stockhawk.ui.DatePicker datePicker = new com.sam_chordas.android.stockhawk.ui.DatePicker();

        datePicker.show(getFragmentManager(), "datePicker");
    }

    @Override
    public void onDateSet(android.widget.DatePicker view, int year, int monthOfYear, int dayOfMonth) {
        // Do something with the date chosen by the user


        monthOfYear += 1;
        String formattedMonth = String.format("%02d", monthOfYear);

        Log.d(getClass().getSimpleName(), "str month : " + formattedMonth);

        String formattedNumber = String.format("%02d", dayOfMonth);

        Log.d(getClass().getSimpleName(), "str day : " + formattedNumber);

        if (fD) {

            inputStartDate = year + "-" + formattedMonth + "-" + formattedNumber;
            Log.d("inputStartDate"
                    , inputStartDate);
            chosenFromDate.setText(context.getString(R.string.from_date) + " " + inputStartDate);
            fD = false;
        } else if (tD) {

            inputEndDate = year + "-" + formattedMonth + "-" + formattedNumber;
            Log.d("inputEndDate", inputEndDate);
            chosenToDate.setText(context.getString(R.string.to_date) + " " + inputEndDate);

            if (inputStartDate != null && inputEndDate != null) {
                dates_check.setVisibility(View.GONE);
                new AsyncHistoricalData().execute(chosenSymbol, inputStartDate, inputEndDate);
            }

            tD = false;
        }
    }


    public class AsyncHistoricalData extends AsyncTask {

        private String JSON_Str;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            progressBar.setVisibility(View.VISIBLE);
        }

        @Override
        protected Object doInBackground(Object[] params) {

            // 0 symbol
            // 1 startDate
            // 2 endDate

            HttpURLConnection urlConnection = null;
            BufferedReader reader = null;


            String COMPLETE_URL = "https://query.yahooapis.com/v1/public/yql?" +
                    "q=select%20*%20from%20yahoo.finance.historicaldata%20" +
                    "where%20symbol%20%3D%20%22" + params[0] +
                    "%22%20and%20startDate%20%3D%20%22" + params[1] + "%22%20and%20endDate%20%3D%20%22" + params[2]
                    + "2010-03-10%22" +
                    "&format=json&diagnostics=true&env=store%3A%2F%2Fdatatables.org%2Falltableswithkeys&callback=";

            try {
                URL url = new URL(COMPLETE_URL);
                Log.d(getClass().getSimpleName(), "async URI : " + url);

                urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestMethod("GET");
                urlConnection.connect();

                // Read the input stream into a String
                InputStream inputStream = urlConnection.getInputStream();
                StringBuffer buffer = new StringBuffer();
                if (inputStream == null) {
                    // Nothing to do.
                    return null;
                }
                reader = new BufferedReader(new InputStreamReader(inputStream));

                String line;
                while ((line = reader.readLine()) != null) {
                    // Since it's JSON, adding a newline isn't necessary (it won't affect parsing)
                    // But it does make debugging a *lot* easier if you print out the completed
                    // buffer for debugging.
                    buffer.append(line + "\n");
                }
                JSON_Str = buffer.toString();

                Log.i(getClass().getSimpleName(), "json :" + JSON_Str);

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (ProtocolException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }


            return JSON_Str;
        }

        @Override
        protected void onPostExecute(Object o) {
            super.onPostExecute(o);
            buttonPanel.setVisibility(View.GONE);
            progressBar.setVisibility(View.GONE);


            Log.d(getClass().getSimpleName(), "async exec obj : " + o);

            try {
                JSONObject jsonObject = new JSONObject(String.valueOf(o));

                jsonObject = jsonObject.getJSONObject("query");
                jsonObject = jsonObject.getJSONObject("results");

                JSONArray jsonArray = jsonObject.getJSONArray("quote");

                ;

                plotGraph(jsonArray);


            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
    }

    private void plotGraph(JSONArray dataPoints) {


        JSONObject jsonObject = null;


        ArrayList<Entry> entries = new ArrayList<>();
        ArrayList<String> labels = new ArrayList<>();

        //Add plots and date labels
        for (int i = 0; i < dataPoints.length(); i++) {
            try {
                jsonObject = dataPoints.getJSONObject(i);

                entries.add(new Entry((float) jsonObject.getDouble("Close"), i));
                labels.add(jsonObject.getString("Date"));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }



        Log.d(getClass().getSimpleName(), "entries : " + entries + "\n"
                + "labels: " + labels);

        //Setup the X axis
        XAxis xAxis = mPlotGraph.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setAvoidFirstLastClipping(true);
        xAxis.setTextColor(android.R.color.white);

        //General chart settings
        mPlotGraph.setDescription(null);
        mPlotGraph.setAutoScaleMinMaxEnabled(true);


        //Setup the Linedata
        LineDataSet lineDataSet = new LineDataSet(entries, "Closed");
        lineDataSet.setDrawValues(false);
        lineDataSet.setDrawHorizontalHighlightIndicator(false);
        lineDataSet.setDrawVerticalHighlightIndicator(false);
        lineDataSet.setCircleRadius(1.5f);

        LineData data = new LineData(labels, lineDataSet);


        mPlotGraph.fitScreen();


        mPlotGraph.setData(data);
        mPlotGraph.invalidate();

        mPlotGraph.animateY(500);

    }
}
