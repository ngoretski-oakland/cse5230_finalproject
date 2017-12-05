package edu.csi5230.ngoretski.finalproject;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

public class CountingActivity extends AppCompatActivity {

    Map<Integer, Integer> countToImageId;
    int targetNumber;
    List<Button> buttons;

    Button button1;
    Button button2;
    Button button3;
    Button button4;

    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_counting);

        countToImageId = new HashMap<>(9);

        countToImageId.put(1, R.drawable.count_one);
        countToImageId.put(2, R.drawable.count_two);
        countToImageId.put(3, R.drawable.count_three);
        countToImageId.put(4, R.drawable.count_four);
        countToImageId.put(5, R.drawable.count_five);
        countToImageId.put(6, R.drawable.count_six);
        countToImageId.put(7, R.drawable.count_seven);
        countToImageId.put(8, R.drawable.count_eight);
        countToImageId.put(9, R.drawable.count_nine);

        buttons = new ArrayList<>();

        button1 = (Button) findViewById(R.id.button_count1);
        button2 = (Button) findViewById(R.id.button_count2);
        button3 = (Button) findViewById(R.id.button_count3);
        button4 = (Button) findViewById(R.id.button_count4);

        button1.setOnClickListener(buildOnClickListener(button1));
        button2.setOnClickListener(buildOnClickListener(button2));
        button3.setOnClickListener(buildOnClickListener(button3));
        button4.setOnClickListener(buildOnClickListener(button4));

        buttons.add(button1);
        buttons.add(button2);
        buttons.add(button3);
        buttons.add(button4);

        imageView = (ImageView) findViewById(R.id.imageView_count);

        setImageAndButtons();
    }

    private View.OnClickListener buildOnClickListener(final Button button) {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (targetNumber == Integer.valueOf(button.getTag().toString())) {
                    setImageAndButtons();

                    //TODO maybe play a sound or something too
                }
            }
        };
    }

    private void setImageAndButtons() {
        int nextCount = new Random().nextInt(9) +1;

        imageView.setImageResource(countToImageId.get(nextCount));

        targetNumber = nextCount;

        Collections.shuffle(buttons);

        Set<Integer> buttonNumbers = new HashSet<>();

        for (Button button : buttons) {
            if (buttons.indexOf(button) == 0) {
                button.setText(String.valueOf(nextCount));
                button.setTag(String.valueOf(nextCount));
                buttonNumbers.add(nextCount);
            }
            else {
                boolean foundNextValue = false;

                while (!foundNextValue) {
                    int newRandom = new Random().nextInt(9) + 1;

                    if (!buttonNumbers.contains(newRandom)) {
                        foundNextValue = true;

                        button.setText(String.valueOf(newRandom));
                        button.setTag(String.valueOf(newRandom));
                        buttonNumbers.add(newRandom);
                    }
                }
            }
        }

    }
}
