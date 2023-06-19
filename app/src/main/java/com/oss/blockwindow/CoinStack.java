package com.oss.blockwindow;

import androidx.appcompat.app.AppCompatActivity;

public class CoinStack extends AppCompatActivity {
    public int CoinAmount;

    public int getCoin() {
        return CoinAmount;
    }

    public void setCoin(int CoinNum) {
        CoinAmount = CoinNum;
    }
}
