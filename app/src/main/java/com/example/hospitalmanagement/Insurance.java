package com.example.hospitalmanagement;

import android.annotation.SuppressLint;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowInsets;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hospitalmanagement.databinding.ActivityInsuranceBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;


public class Insurance extends AppCompatActivity {

    private TextInputEditText phone, name, gender, age, address;
    private Button regButton;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insurance);

        phone = findViewById(R.id.insurancePhoneNumber);
        age = findViewById(R.id.insuranceAge);
        name = findViewById(R.id.insuranceFullname);
        gender = findViewById(R.id.insuranceGender);
        address = findViewById(R.id.insuranceAddress);

        regButton = findViewById(R.id.insuranceSaveBtn);


        regButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String uid = getIntent().getStringExtra("uid");

                String phoneNumber = phone.getText().toString();
                String ageN = age.getText().toString();
                String nameN = name.getText().toString();
                String addressN = address.getText().toString();
                String genderN = gender.getText().toString();

                HashMap insurancedata = new HashMap();

                insurancedata.put("phone", phoneNumber);
                insurancedata.put("name", nameN);
                insurancedata.put("address", addressN);
                insurancedata.put("gender", genderN);
                insurancedata.put("age", ageN);

                databaseReference = FirebaseDatabase.getInstance().getReference().child("Insurance").child(uid);

                databaseReference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        databaseReference.updateChildren(insurancedata).addOnCompleteListener(new OnCompleteListener() {
                            @Override
                            public void onComplete(@NonNull Task task) {
                                if (task.isSuccessful()){
                                    Toast.makeText((Insurance.this), "Insurance Information Saved Successfully", Toast.LENGTH_SHORT).show();
                                    finish();
                                }
                                else{
                                    Toast.makeText(Insurance.this, "Could not save Information at the moment", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

            }
        });
    }
}