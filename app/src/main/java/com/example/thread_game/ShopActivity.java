package com.example.thread_game;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class ShopActivity extends AppCompatActivity {

    private CoinStack sharedCoin;

    public void CoinTotal(CoinStack CoinAmount) {
        sharedCoin = CoinAmount;
    }

    public int getCoin() {
        return sharedCoin.getCoin();
    }

    private static final int REQUEST_CODE = 1;
    private static final int ITEM_EXTRA_LIFE = 5000;
    private static final int ITEM_COIN_X2 = 5000;
    private static final int ITEM_SCORE_X2 = 10000;


    //xml 버튼 누르면 해당하는 아이템 구매하게끔 만드는 코드.
    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.shop);

        Button buyButton_life = findViewById(R.id.buy_button_life);
        Button buyButton_X2Coin = findViewById(R.id.buy_X2Coin);
        Button buyButton_X2Score = findViewById(R.id.buy_X2Score);


        buyButton_life.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buyItem_lifeup();
            }
        });
        buyButton_X2Score.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { buyItem_X2Coin();}
        });
        buyButton_X2Score.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {buyItem_X2Score();}
        });
        buyButton_X2Coin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {buyItem_X2Coin();}
        });
    }


    /*아이템 종류별 구매 구현 */

    private void buyItem_lifeup() {
        // 아이템을 구매하고 GameActivity로 데이터를 전달
        int remainingCoins = getRemainingCoins(); // 구매 후 남은 코인
        int remainingLives = getRemainingLives(); // 구매 후 남은 라이프

        if (remainingCoins >= ITEM_EXTRA_LIFE) {
            // 아이템 구매 가능
            remainingCoins -= ITEM_EXTRA_LIFE;
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

    private void buyItem_X2Coin() {
        int remainingCoins = getRemainingCoins(); // 구매 후 남은 코인
        int remainingLives = getRemainingLives(); // 구매 후 남은 라이프

        if (remainingCoins >= ITEM_COIN_X2) {
            // 아이템 구매 가능
            remainingCoins -= ITEM_COIN_X2;
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

    private void buyItem_X2Score() {
        int remainingCoins = getRemainingCoins(); // 구매 후 남은 코인
        int remainingLives = getRemainingLives(); // 구매 후 남은 라이프

        if (remainingCoins >= ITEM_SCORE_X2) {
            // 아이템 구매 가능
            remainingCoins -= ITEM_SCORE_X2;
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
        int CoinNum = sharedCoin.getCoin();
        sharedCoin.setCoin(CoinNum);
        return CoinNum;
    }

    private int getRemainingLives() {
        // 라이프 개수를 가져오는 로직을 여기에 구현
        // 예시로 5개의 라이프로 가정
        return 5;
    }
}
//아이템 로직만 구현하자.