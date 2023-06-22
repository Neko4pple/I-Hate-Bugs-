package com.oss.blockwindow;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ShopActivity extends AppCompatActivity {

    private int cn;

    private static final int ITEM_EXTRA_LIFE = 5000;
    private static final int ITEM_COIN_X2 = 5000;
    private static final int ITEM_SCORE_X2 = 10000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.shop);

        SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        cn = sharedPreferences.getInt("coinCount", 0);

        Intent intent = getIntent();
        if (intent != null) {
            int gameCoin = intent.getIntExtra("gameCoin", 0);
            cn += gameCoin;
        }

        Button buyButton_X2Coin = findViewById(R.id.buy_X2Coin);
        Button buyButton_X2Score = findViewById(R.id.buy_X2Score);

        buyButton_X2Coin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buyItem_X2Coin();
            }
        });

        buyButton_X2Score.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buyItem_X2Score();
            }
        });

        TextView coinTextView = findViewById(R.id.coinTextView);
        coinTextView.setText("Coins: " + cn);
    }

    // 나머지 코드...

    @Override
    protected void onResume() {
        super.onResume();

        // SharedPreferences에서 코인 값을 누적
        SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        int previousCoinCount = sharedPreferences.getInt("coinCount", 0);
        cn += previousCoinCount;

        // SharedPreferences 업데이트
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("coinCount", cn);
        editor.apply();

        // TextView에 코인 값 업데이트
        TextView coinTextView = findViewById(R.id.coinTextView);
        coinTextView.setText("Coins: " + cn);
    }


    @Override
    protected void onPause() {
        super.onPause();

        SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("coinCount", cn); // 변경된 코인 수량 저장
        editor.apply();
    }

    private void buyItem_X2Coin() {
        int remainingCoins = cn; // 구매 후 남은 코인

        if (remainingCoins >= ITEM_COIN_X2) {
            // 아이템 구매 가능
            remainingCoins -= ITEM_COIN_X2;

            // GameActivity로 데이터 전달
            Intent intent = new Intent();
            intent.putExtra("remainingCoins", remainingCoins);
            setResult(RESULT_OK, intent);
        } else {
            // 코인 부족한 경우 처리
            setResult(RESULT_CANCELED);
        }

        finish();
    }

    private void buyItem_X2Score() {
        int remainingCoins = cn; // 구매 후 남은 코인

        if (remainingCoins >= ITEM_SCORE_X2) {
            // 아이템 구매 가능
            remainingCoins -= ITEM_SCORE_X2;

            // GameActivity로 데이터 전달
            Intent intent = new Intent();
            intent.putExtra("remainingCoins", remainingCoins);
            setResult(RESULT_OK, intent);
        } else {
            // 코인 부족한 경우 처리
            setResult(RESULT_CANCELED);
        }

        finish();
    }
} //코인 연동