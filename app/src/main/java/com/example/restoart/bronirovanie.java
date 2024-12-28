package com.example.restoart;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class bronirovanie extends AppCompatActivity {
    private DatabaseBron dbHelper;
    private EditText editTextTableNumber, editTextDate, editTextName;
    private Button buttonSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bronirovanie);

        Button nazad = findViewById(R.id.nazad);

        nazad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(bronirovanie.this, glavnoe.class);
                startActivity(intent);
                finish(); // Закрывает текущую активити
            }
        });

        dbHelper = new DatabaseBron(this);
        editTextTableNumber = findViewById(R.id.editTextTableNumber);
        editTextDate = findViewById(R.id.editTextDate);
        editTextName = findViewById(R.id.editTextName);
        buttonSave = findViewById(R.id.buttonSave);
        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tableNumber = editTextTableNumber.getText().toString().trim();
                String date = editTextDate.getText().toString().trim();
                String name = editTextName.getText().toString().trim();

                // Проверяем, что все поля заполнены
                if (!tableNumber.isEmpty() && !date.isEmpty() && !name.isEmpty()) {
                    dbHelper.insertData(tableNumber, date, name);

                    Intent intent = new Intent(bronirovanie.this, zabron.class);
                    intent.putExtra("NAME", name);
                    intent.putExtra("TABLE_NUMBER", tableNumber);
                    intent.putExtra("DATE", date);
                    startActivity(intent);

                    // Очищаем поля ввода
                    editTextTableNumber.setText("");
                    editTextDate.setText("");
                    editTextName.setText("");
                } else {
                }
            }
        });
    }
}
