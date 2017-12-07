package edu.csi5230.ngoretski.finalproject;

import android.content.Context;
import android.media.MediaPlayer;
import android.os.Handler;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;

/**
 * This
 */
public class PoppingPresentThread extends  Thread {

    private final int VELOCITY = 30;

    private final Handler handler;
    private final Context context;
    private final ImageButton button;
    private final int screeWidth;
    private final int screeHeight;
    private final ViewGroup parent;
    private final MediaPlayer player;

    private boolean threadDone = false;

    public PoppingPresentThread (Handler handler, Context context, final ViewGroup parent, final ImageButton button, int screeWidth, int screeHeight, final MediaPlayer player) {
        this.handler = handler;
        this.context = context;
        this.button = button;
        this.screeWidth = screeWidth;
        this.screeHeight = screeHeight;
        this.parent = parent;
        this.player = player;

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                player.start();

                threadDone = true;
                parent.removeView(button);
            }
        });
    }

    @Override
    public void run() {
        int currentY = (int)button.getY();

        if (currentY >= screeHeight) {
            threadDone = true;
            parent.removeView(button);
        }
        else {
            int nextY = currentY + VELOCITY;

            button.animate().translationY(nextY).setDuration(95).start();
        }

        if (!threadDone) {
            handler.postDelayed(this, 100);
        }
    }

}
