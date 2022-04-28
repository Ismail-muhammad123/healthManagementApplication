package com.example.hospitalmanagement;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class DoctorRegistrationActivity extends AppCompatActivity {

    private TextView regQeustion;
    private TextInputEditText registerFullname, registerPhoneNumber, registerIdnumber, registerEmail, registerPassword;
    private Button regButton;
    private Spinner availabilitySpinner, departmentSpinner;


    private FirebaseAuth mAuth;
    private DatabaseReference userDatabaseRef;
    private ProgressDialog loader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_registration);

        regQeustion = findViewById(R.id.regQeustion);
        regQeustion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DoctorRegistrationActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });

        registerFullname = findViewById(R.id.registerFullname);
        registerPhoneNumber = findViewById(R.id.registerPhoneNumber);
        registerIdnumber = findViewById(R.id.registerIdnumber);
        registerEmail = findViewById(R.id.registerEmail);
        registerPassword = findViewById(R.id.registerPassword);
        availabilitySpinner = findViewById(R.id.availabilitySpinner);
        departmentSpinner = findViewById(R.id.departmentSpinner);
        regButton = findViewById(R.id.regButton);

        loader = new ProgressDialog(this);
        mAuth = FirebaseAuth.getInstance();

        regButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String name = registerFullname.getText().toString().trim();
                final String phoneNumber = registerPhoneNumber.getText().toString().trim();
                final String idNumber = registerIdnumber.getText().toString().trim();
                final String email = registerEmail.getText().toString().trim();
                final String password = registerPassword.getText().toString().trim();
                final String availability = availabilitySpinner.getSelectedItem().toString();
                final String department = departmentSpinner.getSelectedItem().toString();

                if (TextUtils.isEmpty(name)){
                    registerEmail.setError("Name Required");
                    return;
                }
                if (TextUtils.isEmpty(phoneNumber)){
                    registerPhoneNumber.setError("PhoneNumber is required");
                    return;
                }
                if (TextUtils.isEmpty(idNumber)){
                    registerIdnumber.setError("ID number is required");
                    return;
                }
                if (TextUtils.isEmpty(email)){
                    registerEmail.setError("Email Required");
                    return;
                }
                if (TextUtils.isEmpty(password)){
                    registerPassword.setError("Password Required");
                    return;
                }
                if (availability.equals("Select time you are available")){
                    Toast.makeText(DoctorRegistrationActivity.this, "Select Your Available Times", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (department.equals("Select departments")){
                    Toast.makeText(DoctorRegistrationActivity.this, "Select Your Department Please", Toast.LENGTH_SHORT).show();
                }
                else {
                    loader.setMessage("Registering You");
                    loader.setCanceledOnTouchOutside(false);
                    loader.show();

                    mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (!task.isSuccessful()){
                                String error = task.getException().toString();
                                Toast.makeText(DoctorRegistrationActivity.this, "Error" + error, Toast.LENGTH_SHORT).show();
                            }
                            else {
                                String currentUserId = mAuth.getCurrentUser().getUid();
                                userDatabaseRef = FirebaseDatabase.getInstance().getReference().child("users").child(currentUserId);
                                HashMap doctorInfo = new HashMap();
                                doctorInfo.put("name", name);
                                doctorInfo.put("phoneNumber", phoneNumber);
                                doctorInfo.put("idNumber", idNumber);
                                doctorInfo.put("email", email);
                                doctorInfo.put("availability", availability);
                                doctorInfo.put("department", department);
                                doctorInfo.put("type", "Doctor");

                                userDatabaseRef.updateChildren(doctorInfo).addOnCompleteListener(new OnCompleteListener() {
                                    @Override
                                    public void onComplete(@NonNull Task task) {
                                        if (task.isSuccessful()){
                                            Toast.makeText(DoctorRegistrationActivity.this, "Doctor Registered Successfully", Toast.LENGTH_SHORT).show();
                                        } else {
                                            Toast.makeText(DoctorRegistrationActivity.this, task.getException().toString(), Toast.LENGTH_SHORT).show();
                                        }
                                        finish();
                                        loader.dismiss();
                                    }
                                });

                            }
                        }
                    });
                }

            }
        });

    }
}