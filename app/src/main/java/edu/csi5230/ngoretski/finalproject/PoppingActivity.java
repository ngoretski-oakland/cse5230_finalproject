package edu.csi5230.ngoretski.finalproject;

import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.TranslateAnimation;
import android.widget.Button;

public class PoppingActivity extends AppCompatActivity {

    ConstraintLayout layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_popping);

        layout = (ConstraintLayout) findViewById(R.id.layout_poping);

        final Button button = new Button(this);
        button.setText("BUTTON");

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("NWG:CLICKED");
            }
        });

        layout.addView(button);

//        button.setTranslationX(100f);
//        button.setTranslationY(100f);

        button.setX(0);
        button.setY(0);

        System.out.println("NWG:window width:"+getWindowManager().getDefaultDisplay().getWidth());
        System.out.println("NWG:window height:"+getWindowManager().getDefaultDisplay().getHeight());

        button.animate().translationX(500).setDuration(5000).start();

//        TranslateAnimation animation = new TranslateAnimation(0, 500, 0, 500);
//        animation.setDuration(5000); // duartion in ms
//        animation.setFillAfter(true);
//        button.startAnimation(animation);

//        new Thread(new Runnable(){
//            @Override
//            public void run() {
//                for (int i = 0; i < 10; i++) {
//                    button.setTranslationY(button.getTranslationY() + 1f);
//
//                    try {
//                        Thread.sleep(1000);
//                    }
//                    catch (Exception e) {
//
//                    }
//                }
//            }
//        }).run();


    }
}
