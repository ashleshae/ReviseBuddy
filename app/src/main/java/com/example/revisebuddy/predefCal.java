package com.example.revisebuddy;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.CalendarView;
import android.widget.TextView;

public class predefCal extends AppCompatActivity {
    TextView curdate,d1,d2,d3;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_predef_cal);

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        CalendarView calendarView= findViewById(R.id.calendarView);
        //calendarView.setBackgroundColor(Color.BLACK);

        curdate= findViewById(R.id.currentDate);
        d1= findViewById(R.id.firstdate);
        d2= findViewById(R.id.seconddate);
        d3= findViewById(R.id.thirdDate);


        calendarView.setOnDateChangeListener((view, year, month, dayOfMonth) -> {

           String  Year = String.valueOf(year);
           String  Month = String.valueOf(month+1);
           String  date = String.valueOf(dayOfMonth);
           String  date2 = String.valueOf(dayOfMonth+1);
           String  date3 = String.valueOf(dayOfMonth+3);
           String  date4 = String.valueOf(dayOfMonth+7);
           curdate.setText(" Selected Date : "+ date+"/"+Month+"/"+Year);
           d1.setText(" 1st Revision : "+ date2+"/"+Month+"/"+Year);
           d2.setText(" 2nd Revision : "+ date3+"/"+Month+"/"+Year);
           d3.setText(" 3rd Revision : "+ date4+"/"+Month+"/"+Year);
        });
    }
}
