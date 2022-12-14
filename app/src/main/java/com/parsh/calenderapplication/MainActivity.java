package com.parsh.calenderapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
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


    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    EmployeeInfo employeeInfo;

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
        Firebase.setAndroidContext(this);
        String UniqueId = Settings.Secure.getString(getApplicationContext().getContentResolver(), Settings.Secure.ANDROID_ID);
        firebase = new Firebase("https://calenderapplication-3035f-default-rtdb.firebaseio.com/Users" + UniqueId);


        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("EmployeeInfo");
        employeeInfo = new EmployeeInfo();

        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {

                String daate = "Date: " + dayOfMonth + "-" + ((month) + 1) + "-" + year;
                String compare = dayOfMonth + "-" + ((month) + 1) + "-" + year;
                compare = compare.replace("-", "");
                date.setText(daate);
                String finalCompare = compare;

                Dialog dialog = new Dialog(MainActivity.this);
                dialog.setContentView(R.layout.slot_dialog);
                Button nine_thirty = dialog.findViewById(R.id.nine_thirty);
                Button eleven_thirty = dialog.findViewById(R.id.eleven_thirty);
                Button one_fourtyfive = dialog.findViewById(R.id.one_fourtyfive);
                Button four = dialog.findViewById(R.id.four);
                Button exit = dialog.findViewById(R.id.exit);
                TextView textView = dialog.findViewById(R.id.Textview);

                nine_thirty.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        textView.setText("ninethirty");
                        databaseReference.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<DataSnapshot> task) {
                                if (task.isSuccessful()) {
                                    if (task.getResult().exists()) {
                                        DataSnapshot dataSnapshot = task.getResult();
                                        String firstName = String.valueOf(dataSnapshot.child("date").getValue());
                                        String slot = String.valueOf(dataSnapshot.child("slot").getValue());
                                        String s = textView.getText().toString();
//                                date.setText(finalCompare+""+firstName);
//                                        date.setText(slot);
                                        if (finalCompare.equals(firstName) && s.equals(slot)) {
                                            Toast.makeText(MainActivity.this, "This slot is already selected", Toast.LENGTH_SHORT).show();
                                        } else {
                                            employeeInfo.setDate(finalCompare);
                                            employeeInfo.setSlot(s);
                                            databaseReference.setValue(employeeInfo);
                                            Toast.makeText(MainActivity.this, "Slot is Successfully selected", Toast.LENGTH_SHORT).show();

                                        }
                                    } else {
                                        Toast.makeText(MainActivity.this, "This date is already selected", Toast.LENGTH_SHORT).show();
                                    }
                                } else {

                                    Toast.makeText(MainActivity.this, "Failed to read", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                    }
                });
                eleven_thirty.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        textView.setText("eleventhirty");
                        databaseReference.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<DataSnapshot> task) {
                                if (task.isSuccessful()) {
                                    if (task.getResult().exists()) {
                                        DataSnapshot dataSnapshot = task.getResult();
                                        String firstName = String.valueOf(dataSnapshot.child("date").getValue());
                                        String slot = String.valueOf(dataSnapshot.child("slot").getValue());
                                        String s = textView.getText().toString();
//                                date.setText(finalCompare+""+firstName);
//                                        date.setText(slot);
                                        if (finalCompare.equals(firstName) && s.equals(slot)) {
                                            Toast.makeText(MainActivity.this, "This slot is already selected", Toast.LENGTH_SHORT).show();
                                        } else {
                                            employeeInfo.setDate(finalCompare);
                                            employeeInfo.setSlot(s);
                                            databaseReference.setValue(employeeInfo);
                                            Toast.makeText(MainActivity.this, "Slot is Successfully selected", Toast.LENGTH_SHORT).show();
                                        }
                                    } else {
                                        Toast.makeText(MainActivity.this, "This date is already selected", Toast.LENGTH_SHORT).show();
                                    }
                                } else {

                                    Toast.makeText(MainActivity.this, "Failed to read", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                    }

                });
                one_fourtyfive.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        textView.setText("onefourtyfive");
                        databaseReference.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<DataSnapshot> task) {
                                if (task.isSuccessful()) {
                                    if (task.getResult().exists()) {
                                        DataSnapshot dataSnapshot = task.getResult();
                                        String firstName = String.valueOf(dataSnapshot.child("date").getValue());
                                        String slot = String.valueOf(dataSnapshot.child("slot").getValue());
                                        String s = textView.getText().toString();
//                                date.setText(finalCompare+""+firstName);
//                                        date.setText(slot);
                                        if (finalCompare.equals(firstName) && s.equals(slot)) {
                                            Toast.makeText(MainActivity.this, "This slot is already selected", Toast.LENGTH_SHORT).show();
                                        } else {
                                            employeeInfo.setDate(finalCompare);
                                            employeeInfo.setSlot(s);
                                            databaseReference.setValue(employeeInfo);
                                            Toast.makeText(MainActivity.this, "Slot is Successfully selected", Toast.LENGTH_SHORT).show();
                                        }
                                    } else {
                                        Toast.makeText(MainActivity.this, "This date is already selected", Toast.LENGTH_SHORT).show();
                                    }
                                } else {

                                    Toast.makeText(MainActivity.this, "Failed to read", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                    }
                });
                four.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        textView.setText("four");
                        databaseReference.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<DataSnapshot> task) {
                                if (task.isSuccessful()) {
                                    if (task.getResult().exists()) {
                                        DataSnapshot dataSnapshot = task.getResult();
                                        String firstName = String.valueOf(dataSnapshot.child("date").getValue());
                                        String slot = String.valueOf(dataSnapshot.child("slot").getValue());
                                        String s = textView.getText().toString();
//                                date.setText(finalCompare+""+firstName);
//                                        date.setText(slot);
                                        if (finalCompare.equals(firstName) && s.equals(slot)) {
                                            Toast.makeText(MainActivity.this, "This date is already selected", Toast.LENGTH_SHORT).show();
                                        } else {
                                            employeeInfo.setDate(finalCompare);
                                            employeeInfo.setSlot(s);
                                            databaseReference.setValue(employeeInfo);
                                            Toast.makeText(MainActivity.this, "Slot is Successfully selected", Toast.LENGTH_SHORT).show();
                                        }
                                    } else {
                                        Toast.makeText(MainActivity.this, "This date is already selected", Toast.LENGTH_SHORT).show();
                                    }
                                } else {

                                    Toast.makeText(MainActivity.this, "Failed to read", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                    }
                });


//                databaseReference.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
//                    @Override
//                    public void onComplete(@NonNull Task<DataSnapshot> task) {
//                        if (task.isSuccessful()) {
//                            if (task.getResult().exists()) {
//                                DataSnapshot dataSnapshot = task.getResult();
//                                String firstName = String.valueOf(dataSnapshot.child("date").getValue());
//                                String slot = String.valueOf(dataSnapshot.child("slot").getValue());
////                                date.setText(finalCompare+""+firstName);
//                                date.setText(slot);
//                                if (finalCompare.equals(firstName)) {
//                                    Toast.makeText(MainActivity.this, "This date is already selected", Toast.LENGTH_SHORT).show();
//                                } else {
//                                    employeeInfo.setDate(finalCompare);
//                                    databaseReference.setValue(employeeInfo);
//                                    Toast.makeText(MainActivity.this, "Date is Successfully selected", Toast.LENGTH_SHORT).show();
//                                }
//                            } else {
//                                Toast.makeText(MainActivity.this, "This date is already selected", Toast.LENGTH_SHORT).show();
//                            }
//                        } else {
//
//                            Toast.makeText(MainActivity.this, "Failed to read", Toast.LENGTH_SHORT).show();
//                        }
//                    }
//                });

        dialog.show();
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