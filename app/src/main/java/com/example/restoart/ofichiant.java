package com.example.restoart;

        import androidx.appcompat.app.AppCompatActivity;
        import android.content.Intent;
        import android.os.Bundle;
        import android.view.View;
        import android.widget.Button;
        import android.widget.EditText;
        import android.widget.TextView;
        import android.widget.Toast;

        import java.util.ArrayList;

public class ofichiant extends AppCompatActivity {
    private EditText ratingInput;
    private TextView averageRatingText1, averageRatingText2, averageRatingText3;
    private Button waiter1Button, waiter2Button, waiter3Button;
    private DatabaseOfic databaseHelper;

    private ArrayList<Integer> ratingsWaiter1 = new ArrayList<>();
    private ArrayList<Integer> ratingsWaiter2 = new ArrayList<>();
    private ArrayList<Integer> ratingsWaiter3 = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ofichiant);
        Button nazad2 = findViewById(R.id.nazad2);
        ratingInput = findViewById(R.id.ratingInput);
        averageRatingText1 = findViewById(R.id.averageRatingText1);
        averageRatingText2 = findViewById(R.id.averageRatingText2);
        averageRatingText3 = findViewById(R.id.averageRatingText3);
        waiter1Button = findViewById(R.id.waiter1Button);
        waiter2Button = findViewById(R.id.waiter2Button);
        waiter3Button = findViewById(R.id.waiter3Button);

        databaseHelper = new DatabaseOfic(this);

        updateAverageRatings();
        nazad2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ofichiant.this, glavnoe.class);
                startActivity(intent);
            }
        });

        waiter1Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submitRating("waiter1");
            }
        });

        waiter2Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submitRating("waiter2");
            }
        });

        waiter3Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submitRating("waiter3");
            }
        });
    }

    private void submitRating(String waiterId) {
        try {
            int rating = Integer.parseInt(ratingInput.getText().toString());
            if (rating >= 1 && rating <= 5) {
                databaseHelper.addRating(waiterId, rating);
                ratingInput.setText("");
                updateAverageRatings();
            } else {
                Toast.makeText(this, "Введите корректную оценку от 1 до 5", Toast.LENGTH_SHORT).show();
            }
        } catch (NumberFormatException e) {
            Toast.makeText(this, "Введите корректную оценку от 1 до 5", Toast.LENGTH_SHORT).show();
        }
    }

    private void updateAverageRatings() {
        averageRatingText1.setText(String.format("Средний рейтинг: %.1f", databaseHelper.getAverageRating("waiter1")));
        averageRatingText2.setText(String.format("Средний рейтинг: %.1f", databaseHelper.getAverageRating("waiter2")));
        averageRatingText3.setText(String.format("Средний рейтинг: %.1f", databaseHelper.getAverageRating("waiter3")));
    }
}
