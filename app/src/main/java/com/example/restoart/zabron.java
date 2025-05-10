package com.example.restoart;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class zabron extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zabron);
        Button nazad3 = findViewById(R.id.nazad3);

        nazad3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(zabron.this, glavnoe.class);
                startActivity(intent);
                finish();
            }
        });

        TextView textView = findViewById(R.id.textView);

        String tableNumber = getIntent().getStringExtra("TABLE_NUMBER");
        String date = getIntent().getStringExtra("DATE");
        String name = getIntent().getStringExtra("NAME");
        StringBuilder message = new StringBuilder("Ваша запись готова!");

        if (tableNumber != null) {
            message.append("\nНомер стола: ").append(tableNumber);
        } else {
            message.append("\nНомер стола: не указан");
        }

        if (date != null) {
            message.append("\nДата: ").append(date);
        } else {
            message.append("\nДата: не указана");
        }

        if (name != null) {
            message.append("\nИмя: ").append(name);
        } else {
            message.append("\nИмя: не указано");
        }
        textView.setText(message.toString());
    }
}
