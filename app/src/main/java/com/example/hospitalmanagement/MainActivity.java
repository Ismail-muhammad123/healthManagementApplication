package com.example.hospitalmanagement;

import static android.util.Log.println;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    private Button allBtn, newBtn, departmentsBtn, messagesBtn;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuth = FirebaseAuth.getInstance();
        allBtn = findViewById(R.id.allBookingsBtn);
        newBtn = findViewById(R.id.newBookingBtn);
        departmentsBtn = findViewById(R.id.open_departments);
        messagesBtn = findViewById(R.id.messages);
        String uid = mAuth.getCurrentUser().getUid();

        allBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    Intent doctorBookings = new Intent(MainActivity.this, DoctorBookings.class);
                    startActivity(doctorBookings);

            }
        });

        departmentsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent depts = new Intent(MainActivity.this, Departments.class);
                startActivity(depts);
            }
        });

        messagesBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                    Intent messageActivity = new Intent(MainActivity.this, Message.class);
                    startActivity(messageActivity);

            }
        });
        newBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    Intent newAppoint = new Intent(MainActivity.this, BookDoctorActivity.class);
                    startActivity(newAppoint);

            }
        });



    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        switch (id){
            case R.id.insurancePage:
                Intent insurancePageIntent = new Intent(MainActivity.this, Insurance.class);
                startActivity(insurancePageIntent);
                return true;

            case R.id.idLogOut:
                Toast.makeText(getApplicationContext(), "User Logged out", Toast.LENGTH_SHORT).show();
                mAuth.signOut();
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent);
                this.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}