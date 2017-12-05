package edu.csi5230.ngoretski.finalproject;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class MatchingActivity extends AppCompatActivity {

    int gridSize = 4;
    GridLayout gridLayout;

    List<Integer> imageIds;

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
        imageIds.add(R.drawable.present);
        imageIds.add(R.drawable.santa);
        imageIds.add(R.drawable.snowman);
        imageIds.add(R.drawable.sock);
        imageIds.add(R.drawable.tree);

        rebuildGrid();
    }

    private void rebuildGrid() {
        gridLayout.setRowCount(gridSize);
        gridLayout.setColumnCount(gridSize);

        Map<Integer, Integer> drawableIdToCount = new HashMap<Integer, Integer>();

        List<Integer> imageIdsToUse = new ArrayList<>();
        for (int i = 0; i < gridSize*2; i++) {

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

        for (int i = 0; i < gridSize; i++) {
            for (int j = 0; j < gridSize; j++) {
                final Button button = new Button(this);

                GridLayout.LayoutParams parem = new GridLayout.LayoutParams(GridLayout.spec(GridLayout.UNDEFINED, 1f), GridLayout.spec(GridLayout.UNDEFINED, 1f));
                button.setLayoutParams(parem);

//                final int sdk = android.os.Build.VERSION.SDK_INT;
//                if(sdk < android.os.Build.VERSION_CODES.JELLY_BEAN) {
//                    button.setBackgroundDrawable( getResources().getDrawable(R.drawable.bells) );
//                } else {
//                    button.setBackground( getResources().getDrawable(R.drawable.bells));
//                }

                int imageId = imageIdsToUse.get(counter);
                counter++;

                button.setTag(String.valueOf(imageId));

                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        final int sdk = android.os.Build.VERSION.SDK_INT;
                        if (sdk < android.os.Build.VERSION_CODES.JELLY_BEAN) {
                            button.setBackgroundDrawable(getResources().getDrawable(Integer.valueOf(button.getTag().toString())));
                        } else {
                            button.setBackground(getResources().getDrawable(Integer.valueOf(button.getTag().toString())));
                        }
                    }
                });

                gridLayout.addView(button);
            }
        }
    }

}
