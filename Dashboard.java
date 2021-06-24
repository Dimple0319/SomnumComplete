package com.noaimnolab.somnum;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.google.firebase.auth.FirebaseAuth;

public class Dashboard extends AppCompatActivity {
    ImageButton imageButton1, imageButton2, imageButton3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dashboard);

        imageButton1 = findViewById(R.id.imageButton5);
        imageButton2 = findViewById(R.id.imageButton8);
        imageButton3 = findViewById(R.id.imageButton9);


        imageButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent (Dashboard.this, Alarm.class);
                startActivity(intent);
                finish();
            }
        });

        imageButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent (Dashboard.this, Music.class);
                startActivity(intent);
                finish();
            }
        });

        imageButton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent (Dashboard.this, Tips.class);
                startActivity(intent);
                finish();
            }
        });
    }
}