package edu.csi5230.ngoretski.finalproject;

import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Handler;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageButton;

import java.util.Random;

public class PoppingActivity extends AppCompatActivity {

    ConstraintLayout layout;
    MediaPlayer player;
    Thread generatorThread;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_popping);

        layout = (ConstraintLayout) findViewById(R.id.layout_poping);

        // handler is just used to set a timer on the thread
        final Handler presentHandler = new Handler() {
        };

        player = MediaPlayer.create(this, R.raw.pop);


        final Handler generatorHandler = new Handler() {
        };

        generatorThread = new Thread() {
            @Override
            public void run() {
                if (interrupted()) {
                    return;
                }

                final ImageButton button = new ImageButton(PoppingActivity.this);
                button.setBackground(null);
                button.setBackgroundResource(R.drawable.present);

                int width = getWindowManager().getDefaultDisplay().getWidth();

                layout.addView(button);

                int startingX = new Random().nextInt(width-20) + 20;

                button.setX(startingX);
                button.setY(0);

                new PoppingPresentThread(presentHandler, getApplicationContext(), layout, button, width, getWindowManager().getDefaultDisplay().getHeight(), player).run();

                int nextSpawn = new Random().nextInt(2000);

                generatorHandler.postDelayed(this, nextSpawn);
            }
        };

        generatorThread.run();

    }

    @Override
    protected void onPause() {
        super.onPause();

        generatorThread.interrupt();
    }
}
