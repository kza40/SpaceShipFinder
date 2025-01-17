package ca.cmpt276.assignment3;

/*
This class is the MainActivity, includes the animations and text that comes onto the screen
once the user opens the app. It also has a countdown timer that takes the user to the MainMenu
if the user hasnt already skipped this session.
 */

import androidx.appcompat.app.AppCompatActivity;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.view.View;
import android.widget.Button;

import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    private static final int Tick = 1000;
    private static final int Complete = 5000;
    private static boolean check = false;

    ImageView starFighter;
    ImageView rebelFighter;
    ImageView rSaber;
    ImageView gSaber;
    TextView gameTitle;
    TextView gameDescription;
    TextView authors;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = new Intent(MainActivity.this, MainMenu.class);

        starFighter = findViewById(R.id.starFighter);
        rebelFighter = findViewById(R.id.rebelFighter);
        rSaber = findViewById(R.id.rSaber);
        gSaber = findViewById(R.id.gSaber);
        gameTitle = findViewById(R.id.gameTitle);
        gameDescription = findViewById(R.id.description);
        authors = findViewById(R.id.authorsText);

        setupMainMenuBtn(intent);
        setupAnim();
    }

    private void setupMainMenuBtn(Intent intent) {
        Button mainMenuBtn = findViewById(R.id.MainMenuBtn);


        mainMenuBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                check = true;
                startActivity(intent);
            }
        });

        new CountDownTimer(Complete, Tick) {

            public void onTick(long millisUntilFinished) {
            }

            public void onFinish() {
                //calls for activity initiation
                activityInitiate(check, intent);
            }
        }.start();
    }

    private void activityInitiate(boolean check, Intent intent){
        if(check==false) {
            startActivity(intent);
            finish();
        }
    }



    private void setupAnim() {


        Animation left = AnimationUtils.loadAnimation(this, R.anim.slide_left);
        Animation right = AnimationUtils.loadAnimation(this, R.anim.slide_right);
        Animation rotateSlideR = AnimationUtils.loadAnimation(this, R.anim.slide_rotate_left);
        Animation rotateSlideL = AnimationUtils.loadAnimation(this, R.anim.slide_rotate_right);
        Animation fadeIn = AnimationUtils.loadAnimation(this, R.anim.fade_in);

        starFighter.startAnimation(left);
        rebelFighter.startAnimation(right);

        rSaber.startAnimation(rotateSlideR);
        gSaber.startAnimation(rotateSlideL);

        gameTitle.startAnimation(fadeIn);
        gameDescription.startAnimation(fadeIn);
        authors.startAnimation(fadeIn);


    }
}

