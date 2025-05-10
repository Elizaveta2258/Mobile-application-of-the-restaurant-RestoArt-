package com.example.restoart;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class glavnoe extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_glavnoe);

        Button bron = findViewById(R.id.bron);
        Button menu = findViewById(R.id.menu);
        Button ofichiant = findViewById(R.id.ofichiant);
        bron.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(glavnoe.this, bronirovanie.class);
                startActivity(intent);
            }
        });
        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(glavnoe.this, menu.class);
                startActivity(intent);
            }
        });
        ofichiant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(glavnoe.this, ofichiant.class);
                startActivity(intent);
            }
        });
    }
}