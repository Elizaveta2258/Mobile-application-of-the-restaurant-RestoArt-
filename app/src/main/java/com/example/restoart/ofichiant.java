package com.example.restoart;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ofichiant extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ofichiant);
        Button nazad2 = findViewById(R.id.nazad2);
        Button oficc1 = findViewById(R.id.oficc1);
        Button oficc2 = findViewById(R.id.oficc2);
        Button oficc3 = findViewById(R.id.oficc3);
        Button buttoneee = findViewById(R.id.buttonеее);

        nazad2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ofichiant.this, glavnoe.class);
                startActivity(intent);
            }
        });
        buttoneee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ofichiant.this, ofik1.class);
                startActivity(intent);
            }
        });
        oficc1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ofichiant.this, ofik1.class);
                startActivity(intent);
            }
        });
        oficc2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ofichiant.this, ofik1.class);
                startActivity(intent);
            }
        });
        oficc3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ofichiant.this, ofik1.class);
                startActivity(intent);
            }
        });
    }
}