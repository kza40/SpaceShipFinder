package ca.cmpt276.assignment3;
/*
This class is the MainMenu where the user can select help section, game settings or start the game.
 */


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainMenu extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        setupHelpBtn();
        setupStartGameBtn();
        setupOptionsBtn();
    }

    private void setupHelpBtn() {
        Button helpBtn = findViewById(R.id.helpBtn);

        helpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainMenu.this, HelpSection.class);
                startActivity(intent);
            }
        });
    }
//
    private void setupStartGameBtn() {
        Button startGameBtn = findViewById(R.id.startGameBtn);

        startGameBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainMenu.this, GameBoard.class);
                startActivity(intent);
            }
        });
    }

    private void setupOptionsBtn() {
        Button optionsBtn = findViewById(R.id.optionsBtn);

        optionsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainMenu.this, GameSetting.class);
                startActivity(intent);
            }
        });
    }
}

