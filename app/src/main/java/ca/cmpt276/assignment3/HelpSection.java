package ca.cmpt276.assignment3;
/*
This class is the help section, where user can find an easy guide on how to play the game
among citations and a link to the cmpt 276 webpage.
 */


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class HelpSection extends AppCompatActivity {
//delete this
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help_section);

        //Make the back button
        Button btn = findViewById(R.id.ReturnToMenu);
        //Create the action for the back button
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        //Make hyperlink
        TextView textView;
        textView = findViewById(R.id.CMPTHyperLink);
        textView.setMovementMethod(LinkMovementMethod.getInstance());

    }
}