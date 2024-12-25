package com.example.restoart;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private SQLiteOpenHelper dbHelper;
    private EditText editTextLogin, editTextPhone, editTextPassword;
    private EditText editTextEmailLogin, editTextEmailPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Инициализация базы данных
        dbHelper = new DatabaseRegistr(this);

        editTextLogin = findViewById(R.id.editTextTextEmailAddress2);
        editTextPhone = findViewById(R.id.editTextPhone);
        editTextPassword = findViewById(R.id.editTextTextPassword2);
        editTextEmailLogin = findViewById(R.id.editTextTextEmailAddress);
        editTextEmailPassword = findViewById(R.id.editTextTextPassword);

        Button buttonRegister = findViewById(R.id.buttonRegister);
        Button buttonVhod2 = findViewById(R.id.buttonVhod2);

        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String login = editTextLogin.getText().toString();
                String phone = editTextPhone.getText().toString();
                String password = editTextPassword.getText().toString();

                if (!login.isEmpty() && !phone.isEmpty() && !password.isEmpty()) {
                    SQLiteDatabase database = dbHelper.getWritableDatabase();
                    String insertQuery = "INSERT INTO users (login, phone, password) VALUES (?, ?, ?)";
                    try {
                        database.execSQL(insertQuery, new String[]{login, phone, password});
                        Toast.makeText(MainActivity.this, "Регистрация успешна!", Toast.LENGTH_SHORT).show();
                    } catch (SQLException e) {
                        Toast.makeText(MainActivity.this, "Ошибка регистрации: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                    } finally {
                        database.close();
                    }
                } else {
                    Toast.makeText(MainActivity.this, "Пожалуйста, заполните все поля", Toast.LENGTH_SHORT).show();
                }
            }
        });

        buttonVhod2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String login = editTextEmailLogin.getText().toString();
                String password = editTextEmailPassword.getText().toString();

                if (!login.isEmpty() && !password.isEmpty()) {
                    SQLiteDatabase database = dbHelper.getReadableDatabase();
                    String selectQuery = "SELECT password FROM users WHERE login = ?";
                    Cursor cursor = null;

                    try {
                        cursor = database.rawQuery(selectQuery, new String[]{login});

                        if (cursor.moveToFirst()) {
                            String dbPassword = cursor.getString(0);
                            if (dbPassword.equals(password)) {
                                Intent intent = new Intent(MainActivity.this, vhod.class);
                                intent.putExtra("LOGIN", login);

                                startActivity(intent);
                            } else {
                                Toast.makeText(MainActivity.this, "Неверный пароль", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(MainActivity.this, "Пользователь не найден", Toast.LENGTH_SHORT).show();
                        }
                    } catch (SQLException e) {
                        Toast.makeText(MainActivity.this, "Ошибка доступа к базе данных: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                    } finally {
                        if (cursor != null) {
                            cursor.close();
                        }
                        database.close();
                    }
                } else {
                    Toast.makeText(MainActivity.this, "Пожалуйста, заполните все поля для входа", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
