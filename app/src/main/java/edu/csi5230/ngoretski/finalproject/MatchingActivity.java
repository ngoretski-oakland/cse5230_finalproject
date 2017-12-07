package edu.csi5230.ngoretski.finalproject;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayout;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class MatchingActivity extends AppCompatActivity {

    int totalColumns = 1;
    GridLayout gridLayout;

    List<Integer> imageIds;
    List<Integer> clickedImageIds;

    Integer lastClicked = null;
    ImageButton lastButton = null;

    int matchCount = 0;

    boolean running = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_matching);

        gridLayout = (GridLayout) findViewById(R.id.gridMatching);

        imageIds = new ArrayList<>();

        imageIds.add(R.drawable.bells);
        imageIds.add(R.drawable.bulb);
        imageIds.add(R.drawable.cookie);
        imageIds.add(R.drawable.house);
        imageIds.add(R.drawable.sled);
        imageIds.add(R.drawable.santa);
        imageIds.add(R.drawable.snowman);
        imageIds.add(R.drawable.sock);
        imageIds.add(R.drawable.tree);
        imageIds.add(R.drawable.candle);

        totalColumns = 1;
        matchCount = 0;

        lastClicked = null;
        lastButton = null;

        running = true;

        rebuildGrid();
    }

    private void rebuildGrid() {
        gridLayout.removeAllViews();

        gridLayout.setRowCount(totalColumns);
        gridLayout.setColumnCount(totalColumns);

        clickedImageIds = new ArrayList<>();

        List<Integer> imageIdsToUse = new ArrayList<>();
        for (int i = 0; i < (totalColumns * (totalColumns+1) )/2; i++) {

            boolean keepLooking = true;

            while (keepLooking) {
                int idToUse = imageIds.get(new Random().nextInt(imageIds.size()));

                if (!imageIdsToUse.contains(idToUse)) {
                    imageIdsToUse.add(idToUse);
                    imageIdsToUse.add(idToUse);
                    keepLooking = false;
                }
            }
        }

        Collections.shuffle(imageIdsToUse);

        int counter = 0;

        for (int i = 0; i < totalColumns; i++) {
            for (int j = 0; j < totalColumns + 1; j++) {
                final ImageButton button = new ImageButton(this);

                GridLayout.LayoutParams parem = new GridLayout.LayoutParams(GridLayout.spec(GridLayout.UNDEFINED, 1f), GridLayout.spec(GridLayout.UNDEFINED, 1f));
                button.setLayoutParams(parem);

                int imageId = imageIdsToUse.get(counter);
                counter++;

                button.setTag(String.valueOf(imageId));

                final int sdk = android.os.Build.VERSION.SDK_INT;
                if (sdk < android.os.Build.VERSION_CODES.JELLY_BEAN) {
                    button.setBackgroundDrawable(getResources().getDrawable(R.drawable.present));
                } else {
                    button.setBackground(getResources().getDrawable(R.drawable.present));
                }

                button.setScaleType(ImageView.ScaleType.CENTER_INSIDE);

                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        final int clickedId = Integer.valueOf(button.getTag().toString());

                        if (!running || clickedImageIds.contains(clickedId) || (lastButton != null && lastButton.equals(button)) ) {
                            return;
                        }

                        final int sdk = android.os.Build.VERSION.SDK_INT;

                        if (sdk < android.os.Build.VERSION_CODES.JELLY_BEAN) {
                            button.setBackgroundDrawable(getResources().getDrawable(clickedId));
                        } else {
                            button.setBackground(getResources().getDrawable(clickedId));
                        }

                        if (lastClicked == null) {
                            lastClicked = clickedId;
                            lastButton = button;
                        }
                        else if (lastClicked == clickedId) {
                            lastClicked = null;
                            lastButton = null;
                            matchCount++;

                            clickedImageIds.add(clickedId);

                            if (matchCount == (totalColumns * (totalColumns + 1))/2) {
                                running = false;

                                final Handler handler = new Handler();
                                handler.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        running = true;

                                        totalColumns++;
                                        matchCount = 0;

                                        lastClicked = null;
                                        lastButton = null;

                                        if (totalColumns > 4) {
                                            totalColumns = 1;
                                        }

                                        rebuildGrid();
                                    }
                                }, 1000);
                            }
                        }
                        else {
                            running = false;

                            final Handler handler = new Handler();
                            handler.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    running = true;

                                    if (sdk < android.os.Build.VERSION_CODES.JELLY_BEAN) {
                                        button.setBackgroundDrawable(getResources().getDrawable(R.drawable.present));
                                        lastButton.setBackgroundDrawable(getResources().getDrawable(R.drawable.present));
                                    } else {
                                        button.setBackground(getResources().getDrawable(R.drawable.present));
                                        lastButton.setBackground(getResources().getDrawable(R.drawable.present));
                                    }

                                    lastClicked = null;
                                    lastButton = null;
                                }
                            }, 1000);
                        }
                    }
                });

                gridLayout.addView(button);
            }
        }
    }

}
