package com.example.hospitalmanagement;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

import de.hdodenhof.circleimageview.CircleImageView;

public class PatientRegistrationActivity extends AppCompatActivity {

    private TextView loginSignUp;
    private TextInputEditText registerFullname, registerPhoneNumber, registerEmail, registerPassword;
    private Button regButton;


    private FirebaseAuth mAuth;
    private DatabaseReference userDatabaseRef;
    private ProgressDialog loader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_registration);

        loginSignUp = findViewById(R.id.loginSignUp);
        loginSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PatientRegistrationActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });

        registerFullname = findViewById(R.id.registerFullname);
        registerPhoneNumber = findViewById(R.id.registerPhoneNumber);
        registerEmail = findViewById(R.id.registerEmail);
        registerPassword = findViewById(R.id.registerPassword);
        regButton = findViewById(R.id.regButton);

        loader = new ProgressDialog(this);
        mAuth = FirebaseAuth.getInstance();

        //when user click on register
        regButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String email = registerEmail.getText().toString().trim();
                final String password = registerPassword.getText().toString().trim();
                final String fullName = registerFullname.getText().toString().trim();
                final String phoneNumber = registerPhoneNumber.getText().toString().trim();

                //check if the inputs are empty
                if (TextUtils.isEmpty(email)){
                    registerEmail.setError("Email Required");
                    return;
                }
                if (TextUtils.isEmpty(password)){
                    registerPassword.setError("Password Required");
                    return;
                }
                if (TextUtils.isEmpty(fullName)){
                    registerFullname.setError("Name Required");
                    return;
                }
                if (TextUtils.isEmpty(phoneNumber)){
                    registerPhoneNumber.setError("Phone Number Required");
                    return;
                }
                else {
                    //registering into database
                    loader.setMessage("Registering You...");
                    loader.setCanceledOnTouchOutside(false);
                    loader.show();

                    mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (!task.isSuccessful()){
                                String error = task.getException().toString();
                                Toast.makeText(PatientRegistrationActivity.this, "Error Occured" + error, Toast.LENGTH_SHORT).show();
                            }
                            else {
                                String currentUserId = mAuth.getCurrentUser().getUid();
                                userDatabaseRef = FirebaseDatabase.getInstance().getReference().child("users").child(currentUserId);
                                HashMap userInfo = new HashMap();
                                userInfo.put("name", fullName);
                                userInfo.put("email", email);
                                userInfo.put("phonenumber", phoneNumber);
                                userInfo.put("type", "Patient");

                                userDatabaseRef.updateChildren(userInfo).addOnCompleteListener(new OnCompleteListener() {
                                    @Override
                                    public void onComplete(@NonNull Task task) {
                                        if (task.isSuccessful()){
                                            Toast.makeText(PatientRegistrationActivity.this, "Details saved successfully", Toast.LENGTH_SHORT).show();
                                        }else {
                                            Toast.makeText(PatientRegistrationActivity.this, task.getException().toString(), Toast.LENGTH_SHORT).show();
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