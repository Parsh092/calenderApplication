package com.parsh.calenderapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
CalendarView calendarView;
TextView date;
Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        calendarView = findViewById(R.id.calendarView);
        date= findViewById(R.id.Date);
button = findViewById(R.id.button);
final String g="Enter Your Details";
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                String daate = "Date: "+dayOfMonth+"-"+((month)+1)+"-"+year;
                String g=daate;
                date.setText(daate);
                Toast.makeText(MainActivity.this,"You have Selected a date",Toast.LENGTH_SHORT).show();
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,appointment.class);
                intent.putExtra("value1",g);
                startActivity(intent);

            }
        });
    }
}