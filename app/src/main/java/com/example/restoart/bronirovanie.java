package com.example.restoart;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;

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

                if (!tableNumber.isEmpty() && !date.isEmpty() && !name.isEmpty()) {
                    if (isDateValid(date)) {
                        if (isNumeric(tableNumber) && isAlphabetic(name)) {
                            if (dbHelper.isTableAvailable(tableNumber, date)) {
                                dbHelper.insertData(tableNumber, date, name);

                                Intent intent = new Intent(bronirovanie.this, zabron.class);
                                intent.putExtra("NAME", name);
                                intent.putExtra("TABLE_NUMBER", tableNumber);
                                intent.putExtra("DATE", date);
                                startActivity(intent);

                                editTextTableNumber.setText("");
                                editTextDate.setText("");
                                editTextName.setText("");
                            } else {
                                Toast.makeText(bronirovanie.this, "Стол " + tableNumber + " уже занят на " + date, Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(bronirovanie.this, "Номер стола должен быть числом", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(bronirovanie.this, "Введите дату в формате дд.мм.гггг", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(bronirovanie.this, "Пожалуйста, заполните все поля", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    private boolean isDateValid(String date) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
        sdf.setLenient(false);
        try {
            sdf.parse(date);
            return true;
        } catch (ParseException e) {
            return false;
        }
    }

    private boolean isNumeric(String str) {
        return str.matches("\\d+");
    }

    private boolean isAlphabetic(String str) {
        return str.matches("[а-яА-ЯёЁa-zA-Z]+");
    }
}
