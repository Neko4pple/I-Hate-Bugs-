package com.oss.blockwindow;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.VideoView;


public class MainActivity extends AppCompatActivity {
    private int cn;
    private TextView coinTextView;
    private static final int GAME_ACTIVITY_REQUEST_CODE = 0;

    VideoView videoView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        cn = sharedPreferences.getInt("coinCount", 0);

        videoView = findViewById(R.id.videoview);
        Uri uri = Uri.parse("android.resource://"+getPackageName()+"/"+R.raw.start);
        videoView.setVideoURI(uri);
        videoView.start();

        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mediaPlayer) {
                mediaPlayer.setLooping(true);
            }
        });
        

        Button startBtn = findViewById(R.id.startBtn);
        Button shopButton = findViewById(R.id.shop);
        Log.i("MainActivity", "1");
        startBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, GameActivity.class);
                intent.putExtra("coinCount", cn); // 코인 수를 GameActivity로 전달
                startActivityForResult(intent, GAME_ACTIVITY_REQUEST_CODE);
            }
        });
        Log.i("MainActivity", "2");
        shopButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this, ShopActivity.class);
                intent.putExtra("coinCount", cn);
                startActivity(intent);
            }
        });
        Log.i("MainActivity", "3");
        //coinTextView = findViewById(R.id.coinTextView);
    }

    @Override
    protected void onPostResume(){
        videoView.resume();
        super.onPostResume();
    }
    @Override
    protected void onRestart(){
        videoView.start();
        super.onRestart();
    }
    @Override
    protected void onPause(){
        videoView.suspend();
        super.onPause();
    }
    @Override
    protected void onDestroy(){
        videoView.stopPlayback();
        super.onDestroy();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == GAME_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK) {
            if (data != null) {
                int coins = data.getIntExtra("remainingcoins", 0);
                cn = coins;

                SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putInt("coinCount", cn);
                editor.apply();
                updateCoinText(cn);
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