package com.parsh.calenderapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.Firebase;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.File;

public class MainActivity extends AppCompatActivity {
    CalendarView calendarView;
    TextView date;
    Button button;
    FirebaseAuth auth;
    FirebaseDatabase database;
    FirebaseStorage storage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        calendarView = findViewById(R.id.calendarView);
        date = findViewById(R.id.Date);
        button = findViewById(R.id.button);
        final String g = "Enter Your Details";
        Firebase firebase;
        auth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        storage = FirebaseStorage.getInstance();
        Firebase.setAndroidContext(this);
        String UniqueId = Settings.Secure.getString(getApplicationContext().getContentResolver(), Settings.Secure.ANDROID_ID);
        firebase = new Firebase("https://calenderapplication-3035f-default-rtdb.firebaseio.com/Users" + UniqueId);

        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                String daate = "Date: " + dayOfMonth + "-" + ((month) + 1) + "-" + year;
                String compare = dayOfMonth + "-" + ((month) + 1) + "-" + year;
                compare = compare.replace("-", "");
                date.setText(daate);
                String finalCompare = compare;
                FirebaseDatabase.getInstance().getReference().
                        child("Date").equalTo(compare).addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                if (snapshot.child("Date").exists()){
                                    Toast.makeText(MainActivity.this,"Date is already selected",Toast.LENGTH_SHORT).show();
                                }
                                else{
                                    Firebase child_date = firebase.child("Date");
                                    child_date.setValue(finalCompare);
                                    Toast.makeText(MainActivity.this, "You have Selected a date", Toast.LENGTH_SHORT).show();
                                }
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {

                            }
                        });
//                Firebase child_date = firebase.child("Date");
//                    child_date.setValue(compare);
//                    Toast.makeText(MainActivity.this, "You have Selected a date", Toast.LENGTH_SHORT).show();
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, appointment.class);
                intent.putExtra("value1", g);
                startActivity(intent);

            }
        });
    }
}