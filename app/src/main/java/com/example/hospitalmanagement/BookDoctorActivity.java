package com.example.hospitalmanagement;

import static android.util.Log.println;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class BookDoctorActivity extends AppCompatActivity {

    private TextInputEditText regPatFullname, regPatPhoneNumber, regPatAddress, regPatEmail, regPatDetails;
    private Spinner availabilityPatSpinner, departmentPatSpinner;
    private Button btnBookPat;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
    private String patientID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_doctor);

        regPatFullname = findViewById(R.id.regPatFullname);
        regPatPhoneNumber = findViewById(R.id.regPatPhoneNumber);
        regPatAddress = findViewById(R.id.regPatAddress);
        regPatEmail = findViewById(R.id.regPatEmail);
        regPatDetails = findViewById(R.id.regPatDetails);
        availabilityPatSpinner = findViewById(R.id.availabilityPatSpinner);
        departmentPatSpinner = findViewById(R.id.departmentPatSpinner);
        btnBookPat = findViewById(R.id.btnBookPat);

        firebaseDatabase = FirebaseDatabase.getInstance();

        databaseReference = firebaseDatabase.getReference("Bookings");

        btnBookPat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String patFullname = regPatFullname.getText().toString().trim();
                String patPhone = regPatPhoneNumber.getText().toString().trim();
                String patAddress = regPatAddress.getText().toString().trim();
                String patEmail = regPatEmail.getText().toString().trim();
                String patDetails = regPatDetails.getText().toString().trim();
                String patAvailable = availabilityPatSpinner.getSelectedItem().toString().trim();
                String patDepartment = departmentPatSpinner.getSelectedItem().toString().trim();
                patientID = patFullname;

                HashMap bookingData  = new HashMap();

                bookingData.put("fullName", patFullname);
                bookingData.put("phoneNumber", patPhone);
                bookingData.put("address", patAddress);
                bookingData.put("email" , patEmail);
                bookingData.put("details", patDetails);
                bookingData.put("available", patAvailable);
                bookingData.put("department", patDepartment);

                databaseReference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        databaseReference.push().setValue(bookingData);

                        Toast.makeText(BookDoctorActivity.this, "Booking Successful", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(BookDoctorActivity.this,MainActivity.class));
                        finish();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Toast.makeText(BookDoctorActivity.this, "Booking Failed", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
}