package edu.csi5230.ngoretski.finalproject;

import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MusicActivity extends AppCompatActivity {

    Button jingleBells;
    Button ohHolyNight;
    Button weWishYou;


    MediaPlayer player;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music);

        jingleBells = (Button) findViewById(R.id.button_jingle_bells);
        jingleBells.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (player != null) {
                    player.stop();
                }

                player = MediaPlayer.create(MusicActivity.this, R.raw.jinglebells);
                player.start();
            }
        });

        ohHolyNight = (Button) findViewById(R.id.button_ohholynight);
        ohHolyNight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (player != null) {
                    player.stop();
                }

                player = MediaPlayer.create(MusicActivity.this, R.raw.ohholynight);
                player.start();
            }
        });

        weWishYou = (Button) findViewById(R.id.button_wewish);
        weWishYou.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (player != null) {
                    player.stop();
                }

                player = MediaPlayer.create(MusicActivity.this, R.raw.wewishyouamerrychristmas);
                player.start();
            }
        });


    }

    @Override
    protected void onPause() {
        super.onPause();
        if (player != null) {
            player.pause();
        }
    }

    @Override
    public void onBackPressed(){
        Intent i = new Intent(this.getApplicationContext(), MainActivity.class);
        i.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        startActivity(i);

        return;
    }

    @Override
    public void onResume() {
        super.onResume();
        if (player != null) {
            player.start();
        }
    }

}
