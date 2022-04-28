package com.example.hospitalmanagement;

import static android.util.Log.println;

import android.annotation.SuppressLint;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowInsets;
import android.widget.TextView;

import com.example.hospitalmanagement.databinding.ActivityMessageBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;


public class Message extends AppCompatActivity {

    TextView messageTxt;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_message);

        messageTxt = findViewById(R.id.messageText);

        messageTxt.setText("No Message Found");

        mAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();

        String uid = mAuth.getCurrentUser().getUid();


        databaseReference = firebaseDatabase.getReference().child("users").child(uid);

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Object fieldsObj = new Object();
                HashMap fldObj;

                try {
                    fldObj = (HashMap) snapshot.getValue(fieldsObj.getClass());

                    String body = fldObj.get("message").toString();

                    if (TextUtils.isEmpty(body)) {
                        messageTxt.setText(body);
                    }
                } catch (Exception ex) {
                    // My custom error handler. See 'ErrorHandler' in Gist
//                ErrorHandler.logError(ex);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}