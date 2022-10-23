package com.parsh.calenderapplication;

import androidx.appcompat.app.AppCompatActivity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class appointment extends AppCompatActivity {
    TextView textView;
    EditText name;
    EditText phone_num;
    EditText email_id;
    EditText vehicle_name;
    EditText vehicle_num;
    EditText address;
    Button submit;
ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appointment);

        textView = findViewById(R.id.textView2);
        name = findViewById(R.id.name);
        phone_num = findViewById(R.id.phone_Num);
        email_id = findViewById(R.id.email_id);
        vehicle_name = findViewById(R.id.vehicle_name);
        vehicle_num = findViewById(R.id.phone_Num);
        address = findViewById(R.id.address);
        submit = findViewById(R.id.submit);
        Bundle extras = getIntent().getExtras();
        String s = extras.getString("value1");
        textView.setText(s);


        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              if (name.getText().toString().trim().equals("")){
                 Toast.makeText(getApplicationContext(),"Your name is empty",Toast.LENGTH_SHORT).show();
             }
             else if (phone_num.getText().toString().trim().equals("")){
                 Toast.makeText(getApplicationContext(),"Your mobile number is empty",Toast.LENGTH_SHORT).show();
             }
             else if (email_id.getText().toString().trim().equals("")){
                  Toast.makeText(getApplicationContext(),"Your email id is empty",Toast.LENGTH_SHORT).show();
              }
             else if (address.getText().toString().trim().equals("")){
                 Toast.makeText(getApplicationContext(),"Your address is empty",Toast.LENGTH_SHORT).show();
             }
             else if (vehicle_name.getText().toString().trim().equals("")){
                 Toast.makeText(getApplicationContext(),"Your vehicle name is empty",Toast.LENGTH_SHORT).show();
             }
             else if (vehicle_num.getText().toString().trim().equals("")){
                 Toast.makeText(getApplicationContext(),"Your vehicle number is empty",Toast.LENGTH_SHORT).show();
             }
             else {
                  Intent intent = new Intent(appointment.this, approval.class);
                  startActivity(intent);
              }
            }
        });



    }
}