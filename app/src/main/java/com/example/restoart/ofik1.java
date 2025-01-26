package com.example.restoart;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ofik1 extends AppCompatActivity {
    Button buttonofik1 = findViewById(R.id.buttonofic1);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ofik1);
        buttonofik1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ofik1.this, ofichiant.class);
                startActivity(intent);
            }
        });
    }
}