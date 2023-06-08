package com.example.thread_game;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {
    private int coinCount = 0;
    private TextView coinTextView;
    private static final int GAME_ACTIVITY_REQUEST_CODE = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button startBtn = findViewById(R.id.startBtn);
        Button shopButton = findViewById(R.id.shop);

        startBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, GameActivity.class);
                intent.putExtra("coinCount", coinCount); // 코인 수를 GameActivity로 전달
                startActivityForResult(intent, 0);
            }
        });

        shopButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ShopActivity.class);
                startActivity(intent);
            }
        });

        coinTextView = findViewById(R.id.coinTextView);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == GAME_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK) {
            if (data != null) {
                int coins = data.getIntExtra("coins", 0);
                updateCoinText(coins);
            }
        }
    }

    private void updateCoinText(int coins) {
        TextView coinTextView = findViewById(R.id.coinTextView);
        coinTextView.setText("Coins: " + coins);
    }

    public void ShopActivity(View view) {
    }
}