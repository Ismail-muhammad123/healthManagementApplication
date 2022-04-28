package com.example.hospitalmanagement;

import static android.content.ContentValues.TAG;
import static android.util.Log.println;

import android.annotation.SuppressLint;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowInsets;
import android.widget.Toast;

import com.example.hospitalmanagement.databinding.ActivityDoctorBookingsBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class DoctorBookings extends AppCompatActivity  implements MyRecyclerViewAdapter.ItemClickListener {
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
    private FirebaseAuth mAuth;
    MyRecyclerViewAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_bookings);
        mAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();

        databaseReference = firebaseDatabase.getReference("Bookings");

        ArrayList<Map> bookings = new ArrayList<>();
        adapter = new MyRecyclerViewAdapter(this, bookings);
        adapter.setClickListener(this);
        RecyclerView recyclerView = findViewById(R.id.bookings_cycleView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Object fieldsObj = new Object();
                HashMap fldObj;

                ArrayList<Map> book = new ArrayList<>();

                for (DataSnapshot bookingData: dataSnapshot.getChildren()) {
                    try{
                        HashMap userInfo = new HashMap();
                        fldObj = (HashMap)bookingData.getValue(fieldsObj.getClass());
                        userInfo.put("email", fldObj.get("email").toString());
                        userInfo.put("uid", bookingData.getKey());

                        book.add(userInfo);

                        adapter.notifyItemChanged(0);
                    }catch (Exception ex){
                        continue;
                    }
                }
                bookings.addAll(book);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Getting Post failed, log a message
                Log.w(TAG, "loadPost:onCancelled", databaseError.toException());
                // ...
            }
        });

        println(Log.INFO,"bookings list:" ,bookings.toString());




    }
    @Override
    public void onItemClick(View view, int position) {
        Intent addMessage = new Intent(DoctorBookings.this, AddMessage.class);
        String uid = mAuth.getCurrentUser().getUid();
        addMessage.putExtra("uid", adapter.getItem(position));
        startActivity(addMessage);
    }

}