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
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class MainActivity extends AppCompatActivity {
    CalendarView calendarView;
    TextView date;
    Button button;
    FirebaseAuth firebaseAuth;
    FirebaseDatabase database;
    FirebaseStorage storage;
    FirebaseFirestore firebaseFirestore;
    private StorageReference storageReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        calendarView = findViewById(R.id.calendarView);
        date = findViewById(R.id.Date);
        button = findViewById(R.id.button);
        final String g = "Enter Your Details";
        Firebase firebase;

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseFirestore = FirebaseFirestore.getInstance();

        database = FirebaseDatabase.getInstance();
        storageReference = FirebaseStorage.getInstance().getReference();
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
//                firebaseFirestore.collection("Users").document(compare).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
//                    @Override
//                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
//                        if (task.getResult().exists()){
//                          Toast.makeText(MainActivity.this,"This date is already selected",Toast.LENGTH_SHORT).show();
//                        }
//                        else{
//                            Firebase child_date = firebase.child("Date");
//                                    child_date.setValue(finalCompare);
//                                    Toast.makeText(MainActivity.this, "You have Selected a date", Toast.LENGTH_SHORT).show();
//
//                        }
//                    }
//                });


                FirebaseDatabase.getInstance().getReference().equalTo(compare).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if (snapshot.child("Date").exists()) {
                            Toast.makeText(MainActivity.this, "Date is already selected", Toast.LENGTH_SHORT).show();
                        } else {
                            Firebase child_date = firebase.child("Date");
                            if (child_date.getKey()== finalCompare) {
                                Toast.makeText(MainActivity.this, "This date is already selected", Toast.LENGTH_SHORT).show();
                            } else {
                                child_date.setValue(finalCompare);
                                Toast.makeText(MainActivity.this, "You have Selected a date", Toast.LENGTH_SHORT).show();
                            }
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