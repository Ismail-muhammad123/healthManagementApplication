package com.example.hospitalmanagement;

import android.annotation.SuppressLint;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowInsets;
import android.widget.Button;
import android.widget.Toast;

import com.example.hospitalmanagement.databinding.ActivityAddMessageBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

public class AddMessage extends AppCompatActivity {

    private TextInputEditText messagetext;
    private FirebaseAuth mAuth;
    private Button sendBtn;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_message);

        messagetext = findViewById(R.id.messageText);
        sendBtn = findViewById(R.id.sendMessageButton);



        firebaseDatabase = FirebaseDatabase.getInstance();
        mAuth = FirebaseAuth.getInstance();

        sendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String messagetxt = messagetext.getText().toString();
                if (TextUtils.isEmpty(messagetxt)) {
                    messagetext.setError("Message cannot be empty");
                }
                String userEmail = mAuth.getCurrentUser().getEmail();
                String email = getIntent().getStringExtra("email");
                Intent intent = new Intent(Intent.ACTION_SEND);

                intent.setType("plain/text");
                intent.putExtra(Intent.EXTRA_EMAIL, email);
                intent.putExtra(Intent.EXTRA_SUBJECT, "Message From Hospitl Mangagement App");
                intent.putExtra(Intent.EXTRA_TEXT, messagetxt);
                intent.putExtra(Intent.EXTRA_REFERRER, email);
                startActivity(Intent.createChooser(intent, "Send mail"));

            }
        });



    }

}