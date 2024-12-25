package com.example.restoart;

import androidx.appcompat.app.AppCompatActivity;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.content.Intent;
import android.os.Handler;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

public class vhod extends AppCompatActivity {

    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vhod);

        TextView textView = findViewById(R.id.textView);
        progressBar = findViewById(R.id.progressBar);

        String login = getIntent().getStringExtra("LOGIN");
        textView.setText("Добро пожаловать, " + login + "!");

        // Показать полосу загрузки и запустить анимацию
        progressBar.setVisibility(View.VISIBLE);

        ObjectAnimator colorAnimator = ObjectAnimator.ofArgb(progressBar, "backgroundColor",
                0xFFFF0000, 0xFF00FF00, 0xFF0000FF); // Красный -> Зеленый -> Синий
        colorAnimator.setDuration(3000); // Длительность анимации
        colorAnimator.setRepeatCount(ObjectAnimator.INFINITE);
        colorAnimator.setRepeatMode(ObjectAnimator.REVERSE);
        colorAnimator.start();

        new Handler().postDelayed(() -> {
            // Скрыть полосу загрузки
            progressBar.setVisibility(View.GONE);
            // Переход на другую активность
            Intent intent = new Intent(vhod.this, glavnoe.class);
            startActivity(intent);
            finish(); // Закрыть текущую активность
        }, 3000); // 5000 миллисекунд = 5 секунд
    }
}//
