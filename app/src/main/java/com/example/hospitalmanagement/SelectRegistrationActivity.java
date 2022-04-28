package com.example.hospitalmanagement;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class SelectRegistrationActivity extends AppCompatActivity {

    private TextView back;
    private Button patientReg, doctorReg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_registration);

        back = findViewById(R.id.back);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SelectRegistrationActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });

        doctorReg = findViewById(R.id.doctorReg);
        patientReg = findViewById(R.id.patientReg);

        patientReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SelectRegistrationActivity.this, PatientRegistrationActivity.class);
                startActivity(intent);
            }
        });

        doctorReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SelectRegistrationActivity.this, DoctorRegistrationActivity.class);
                startActivity(intent);
            }
        });

    }
}