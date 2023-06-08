package com.example.thread_game;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class ShopActivity extends AppCompatActivity {

    private static final int REQUEST_CODE = 1;
    private static final int ITEM_PRICE = 5000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.shop);

        Button buyButton = findViewById(R.id.buy_button);
        buyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buyItem();
            }
        });
    }

    private void buyItem() {
        // 아이템을 구매하고 GameActivity로 데이터를 전달
        int remainingCoins = getRemainingCoins(); // 구매 후 남은 코인
        int remainingLives = getRemainingLives(); // 구매 후 남은 라이프

        if (remainingCoins >= ITEM_PRICE) {
            // 아이템 구매 가능
            remainingCoins -= ITEM_PRICE;
            remainingLives++;

            // GameActivity로 데이터 전달
            Intent intent = new Intent();
            intent.putExtra("remainingCoins", remainingCoins);
            intent.putExtra("remainingLives", remainingLives);
            setResult(RESULT_OK, intent);
        } else {
            // 코인 부족한 경우 처리
            setResult(RESULT_CANCELED);
        }

        finish();
    }

    private int getRemainingCoins() {
        // 코인 잔액을 가져오는 로직을 여기에 구현
        // 예시로 10000 코인으로 가정
        return 10000;
    }

    private int getRemainingLives() {
        // 라이프 개수를 가져오는 로직을 여기에 구현
        // 예시로 5개의 라이프로 가정
        return 5;
    }
}
